package com.system.daisy.entity;

import java.util.ArrayList;

public class PersonalCartItems {
    private String email;
    private ArrayList<CartItem> cartItems;

    public PersonalCartItems() {

    }

    public PersonalCartItems(String email, ArrayList<CartItem> cartItems) {
        this.email = email;
        this.cartItems = cartItems;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
