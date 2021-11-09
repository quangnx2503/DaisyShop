package com.system.daisy.entity;

public class Advertisement {
    private int id;
    private int productId;
    private String mediaUrl;
    private boolean status;

    public Advertisement() {
    }

    public Advertisement(int id, int productId, String mediaUrl, boolean status) {
        this.id = id;
        this.productId = productId;
        this.mediaUrl = mediaUrl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
