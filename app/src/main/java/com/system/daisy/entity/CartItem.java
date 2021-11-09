package com.system.daisy.entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private String name;
    private String url;
    private int quantity;
    private int price;
    private int sale;

    public CartItem() {
    }

    public CartItem(int id, String name, String url, int quantity, int price, int sale) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageId=" + url +
                ", quantity=" + quantity +
                ", price=" + price +
                ", sale=" + sale +
                '}';
    }
}
