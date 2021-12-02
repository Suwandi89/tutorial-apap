import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button"
import Modal from "../../components/modal";
import { Fab } from "@mui/material";
import { Badge } from "@mui/material";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            cartHidden: true,
            cartItems: [],            
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            qtyinput: 0,
            keyword: "",
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleCheckout = this.handleCheckout.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
        this.handleAddItemToCart = this.handleAddItemToCart.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleSearchItem = this.handleSearchItem.bind(this);
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }
        
    handleAddItem() {
        this.setState({ isCreate:true });
    }
        
    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit: false });
    }

    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }
    
    handleToggle() {
        const cartHidden = this.state.cartHidden;
        this.setState({cartHidden: !cartHidden});
    };

    async handleAddItemToCart(item) {
        if (item.quantity >= this.state.qtyinput){
            try {
                const data = {
                    idItem: item.id,
                    quantity: this.state.qtyinput,
                };
                await APIConfig.post(`/cart`, data);
                this.setState({
                    id: "",
                    title: "",
                    price: 0,
                    description: "",
                    category: "",
                    quantity: 0,
                })
                this.loadData();
                this.loadCart();
            } catch (error) {
                alert("Oops terjadi masalah pada server");
                console.log(error);
            }
        } else {
            alert("Stok tidak memenuhi!"); 
        }
    };

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
        
    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }    

    async handleSearchItem(event) {
        event.preventDefault();
        try {
            const { data } = await APIConfig.get(`/item?title=${this.state.keyword}`);
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleCheckout(event) {
        event.preventDefault();
        try {
            await APIConfig.get("/cart/checkout");
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0,
            })
            this.loadData();
            this.loadCart();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    componentDidMount() {
        this.loadData();
        this.loadCart();
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async loadCart() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ cartItems: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    render() {
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>
                    {this.state.cartHidden ? "All Items" : "Cart Items"}
                </h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    {this.state.cartHidden ? (
                        <Fab variant="extended" onClick={this.handleToggle}>
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                        </Fab>
                    ) : (
                        <div>
                            {this.state.cartItems.length ? (
                                <Fab variant="extended" onClick={this.handleCheckout}>
                                    CHECKOUT
                                </Fab>
                            ) : ""}
                        </div>
                    )}   
                </div>
                <div style={{ position: "fixed", top: 25, left: 25 }}>
                    {this.state.cartHidden ? (
                        ""
                    ) : (
                        <Fab variant="extended" onClick={this.handleToggle}>
                            Back
                        </Fab>
                    )}
                </div>
                {this.state.cartHidden ? (
                    <div>
                        <Button action={this.handleAddItem}>
                            Add Item
                        </Button>
                        <form>
                            <div>
                                <input
                                    className={classes.textField}
                                    type="text"
                                    placeholder="Tuliskan nama item"
                                    name="keyword"
                                    onChange={this.handleChangeField}
                                />
                                <Button action={this.handleSearchItem}
                                >
                                    Cari
                                </Button>
                            </div>
                        </form>
                    </div>
                ) : ""}
                <div>
                    {!this.state.cartHidden ? (
                        <div className="col-sm">
                            {this.state.cartItems.map((item) => (
                                <Item
                                key={item.id}
                                id={item.id}
                                title={item.item.title}
                                price={item.item.price}
                                description={item.item.description}
                                category={item.item.category}
                                quantity={item.quantity}
                                cartHid={this.state.cartHidden}
                                />
                            ))}
                        </div>
                    ) : <div>
                        {this.state.items.map((item) => (
                            <Item
                            key={item.id}
                            id={item.id}
                            title={item.title}
                            price={item.price}
                            description={item.description}
                            category={item.category}
                            quantity={item.quantity}
                            handleEdit={() => this.handleEditItem(item)}
                            handleChange={this.handleChangeField}
                            handleAdd={() => this.handleAddItemToCart(item)}
                            cartHid={this.state.cartHidden}
                            />
                        ))}
                    </div>}      
                </div>
                <Modal
                show={this.state.isCreate || this.state.isEdit}
                handleCloseModal={this.handleCancel}
                modalTitle={this.state.isCreate
                    ? "Add Item"
                    : `Edit Item ID ${this.state.id}`}
                >
                    <form>
                        <input
                        className={classes.textField}
                        type="text"
                        placeholder="Nama Item"
                        name="title"
                        value={this.state.title}
                        onChange={this.handleChangeField}
                        />
                        <input
                        className={classes.textField}
                        type="number"
                        placeholder="Harga"
                        name="price"
                        value={this.state.price}
                        onChange={this.handleChangeField}
                        />
                        <textarea
                        className={classes.textField}
                        placeholder="Deskripsi"
                        name="description"
                        rows="4"
                        value={this.state.description}
                        onChange={this.handleChangeField}
                        />
                        <input
                        className={classes.textField}
                        type="text"
                        placeholder="Kategori"
                        name="category"
                        value={this.state.category}
                        onChange={this.handleChangeField}
                        />
                        <input
                        className={classes.textField}
                        type="number"
                        placeholder="qty"
                        name="quantity"
                        value={this.state.quantity}
                        onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate
                        ? this.handleSubmitItem
                        : this.handleSubmitEditItem}
                        >
                            Create
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}   

export default ItemList;