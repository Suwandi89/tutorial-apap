import React from "react";
import Button from "../button";
import classes from "./styles.module.css";

function multiply(num1, num2){
	return num1 * num2;
}

const Item = (props) => {
    const { id, title, price, description, category, quantity, handleEdit, handleDelete, handleAdd, handleChange, cartHid} = props;
    return (
        <div className={classes.item}>
            {cartHid ? (
                <div>
                    <h3>{`ID ${id}`}</h3>
                    <p>{`Nama Barang: ${title}`}</p>
                    <p>{`Harga: ${price}`}</p>
                    <p>{`Deskripsi: ${description}`}</p>
                    <p>{`Kategori: ${category}`}</p>
                    <p>{`stok: ${quantity}`}</p>
                    <Button action={handleEdit}>
                        Edit
                    </Button>
                    <form>
                        <div>
                            <input 
                                type="hidden"
                                name="idItem"
                                value={id}
                            />
                            <input
                                className={classes.textField}
                                type="number"
                                placeholder="Tuliskan jumlah quantity"
                                name="qtyinput"
                                onChange={handleChange}
                            />
                            <span>
                            <Button action={handleAdd}
                            >
                                Add To Cart
                            </Button>
                            </span>
                        </div>
                    </form>
                </div>
            ) : (
                <div>
                    <h3>{`ID CART: ${id}`}</h3>
                    <p>{`Nama Barang: ${title}`}</p>
                    <p>{`Harga: ${price}`}</p>
                    <p>{`Jumlah: ${quantity}`}</p>
                    <p>{`Deskripsi: ${description}`}</p>
                    <p>{`Kategori: ${category}`}</p>
                    <p><b>{`Total Harga: ${multiply(price,quantity)}`}</b></p>
                </div>
            )}
        </div>
    );
};
export default Item;