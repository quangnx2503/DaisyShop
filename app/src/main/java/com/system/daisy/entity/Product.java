package com.system.daisy.entity;

import java.sql.Date;

public class Product {
    Integer id;
    String name;
    String description;
    int status;
    float sale;
    int salepriority;
    int price;
    int clicktimes;
    Date createtime;
    String producer;
    String origin;
    String guarantee;
    String specifictions;
    String avatar;

    public Product() {

    }

    public Product(Integer id, String name, String description, int status, float sale, int salepriority, int price, int clicktimes, Date createtime, String producer, String origin, String guarantee, String specifictions, String avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.sale = sale;
        this.salepriority = salepriority;
        this.price = price;
        this.clicktimes = clicktimes;
        this.createtime = createtime;
        this.producer = producer;
        this.origin = origin;
        this.guarantee = guarantee;
        this.specifictions = specifictions;
        this.avatar = avatar;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public int getClicktimes() {
        return clicktimes;
    }

    public void setClicktimes(int clicktimes) {
        this.clicktimes = clicktimes;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public String getSpecifictions() {
        return specifictions;
    }

    public void setSpecifictions(String specifictions) {
        this.specifictions = specifictions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
