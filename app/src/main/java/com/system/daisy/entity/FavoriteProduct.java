package com.system.daisy.entity;

import java.util.Date;

public class FavoriteProduct {
    private int id;
    private String email;
    private String name;
    private String imageURL;
    private Date time;
    private int price;
    private int sale;

    public FavoriteProduct() {

    }

    public FavoriteProduct(int id, String email, String name, String imageURL, Date time, int price, int sale) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.imageURL = imageURL;
        this.time = time;
        this.price = price;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
}
