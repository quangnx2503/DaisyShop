package com.system.daisy.entity;

import java.util.Date;

public class Products {
    private int id;
    private String name;
    private String description;
    private boolean status;
    private float sale;
    private int salepriority;
    private int price;
    private int clickTimes;
    private Date createTime;
    private String producer;
    private String origin;
    private String guarantee;
    private String specifications;
    private String imageUrl;

    public Products() {
    }

    public Products(int id, String name, String description, boolean status, float sale, int salepriority, int price, int clickTimes, Date createTime, String producer, String origin, String guarantee, String specifications, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.sale = sale;
        this.salepriority = salepriority;
        this.price = price;
        this.clickTimes = clickTimes;
        this.createTime = createTime;
        this.producer = producer;
        this.origin = origin;
        this.guarantee = guarantee;
        this.specifications = specifications;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public int getSalepriority() {
        return salepriority;
    }

    public void setSalepriority(int salepriority) {
        this.salepriority = salepriority;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getClickTimes() {
        return clickTimes;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
