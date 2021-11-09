package com.system.daisy.entity;

public class ProductSubcategory {
    private int productId;
    private int subcategoryId;

    public ProductSubcategory() {
    }

    public ProductSubcategory(int productId, int subcategoryId) {
        this.productId = productId;
        this.subcategoryId = subcategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
