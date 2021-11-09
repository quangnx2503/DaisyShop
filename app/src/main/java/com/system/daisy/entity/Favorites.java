package com.system.daisy.entity;

import java.util.Date;

public class Favorites {
    private String email;
    private int productId;
    private Date time;

    public Favorites() {
    }

    public Favorites(String email, int productId, Date time) {
        this.email = email;
        this.productId = productId;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
