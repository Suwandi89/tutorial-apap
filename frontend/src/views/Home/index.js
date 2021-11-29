import React from "react";
import ListItems from "../../items.json"
import List from "../../components/List/index"
import { Fab } from "@mui/material";
import { Badge } from "@mui/material";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { ViewStreamIcon } from '@icons/material';

import "./index.css"

export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shopItems: ListItems,
            cartItems: [],
            cartHidden: true,
            balance: 120,
        };
    }

    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems];
        const newItem = { ...item };
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);
        if (this.state.balance > item.price){
            if  (targetInd < 0) {
                newItem.inCart = true;
                newItems.push(newItem);
                this.updateShopItem(newItem, true);
            }
            this.setState({ cartItems: newItems });
            this.setState({ balance: this.state.balance-item.price})
        } else {
            alert("Balance not sufficient!"); 
        }
    };

    handleRemoveItemFromCart = (item) => {
        const newItems = [...this.state.cartItems];
        const removeItem = { ...item };
        const targetInd = newItems.findIndex((it) => it.id === removeItem.id);
        removeItem.inCart = false;
        newItems.splice(targetInd, 1);
        this.updateShopItem(removeItem, false);
        this.setState({ cartItems: newItems });
        this.setState({ balance: this.state.balance+item.price})
    }

    handleRemoveAllItemFromCart = (item) => {
        const newItems = [...this.state.cartItems];
        var i;
        var bal = this.state.balance;
        for (i = 0; i < newItems.length; i++){
            const removeItem = newItems[i];
            const targetInd = newItems.findIndex((it) => it.id === removeItem.id);
            removeItem.inCart = false;
            newItems.splice(targetInd, 1);
            this.updateShopItem(removeItem, false);
            bal += item.price
        }
        this.setState({ cartItems: newItems });
        this.setState({ balance: bal})
    }

    updateShopItem = (item, inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({shopItems: tempShopItems});
    }

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({cartHidden: !cartHidden});
    };
    
    render() {
        return (
            <div className="container-fluid">
                <h1 className="text-center mt-3 mb-0>">Mini Commerce</h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    <Fab variant="extended" onClick={this.handleToggle}>
                        {this.state.cartHidden ?
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                            : <ViewStreamIcon/>}
                    </Fab>
                </div>
                <p className="text-center text-secondary text-sm font-italic"> 
                    (this is a <strong> class-based </strong> application)
                </p>
                <p className="text-center text-primary" >Your Balance: <b> {this.state.balance}</b> </p>
                <div className="container pt-3">
                    <div className="row mt-3">
                        {!this.state.cartHidden ? (
                            <div className="col-sm">
                                <button onClick={this.handleRemoveAllItemFromCart}>Remove All</button>
                                <List 
                                    title="My Cart" 
                                    items={this.state.cartItems}
                                    onItemClick={this.handleRemoveItemFromCart}
                                ></List>
                            </div>
                        ) : <div className="col-sm">
                            <List 
                                title="List Items" 
                                items={this.state.shopItems}
                                onItemClick={this.handleAddItemToCart}
                                isShopList={true}
                            ></List>
                        </div>}
                    </div>
                </div>
            </div>
        );
    }
}