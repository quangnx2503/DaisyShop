package com.system.daisy.entity;

public class ProductPictures {
    private int productId;
    private String imageUrl;

    public ProductPictures() {
    }

    public ProductPictures(int productId, String imageUrl) {
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
