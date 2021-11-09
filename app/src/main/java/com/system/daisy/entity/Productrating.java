package com.system.daisy.entity;

public class Productrating {
    private int productid;
    private String email;
    private String ratingContent;
    private float stars;

    public Productrating() {
    }

    public Productrating(int productid, String email, String ratingContent, float stars) {
        this.productid = productid;
        this.email = email;
        this.ratingContent = ratingContent;
        this.stars = stars;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRatingContent() {
        return ratingContent;
    }

    public void setRatingContent(String ratingContent) {
        this.ratingContent = ratingContent;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
