package com.system.daisy.entity;

import java.util.Date;

public class Orders {
    private int id;
    private String email;
    private Date orderTime;
    private Date shipTime;
    private String destination;
    private int totalPrice;
    private boolean status;
    private int paymentmethod;

    public Orders() {
    }

    public Orders(int id, String email, Date orderTime, Date shipTime, String destination, int totalPrice, boolean status, int paymentmethod) {
        this.id = id;
        this.email = email;
        this.orderTime = orderTime;
        this.shipTime = shipTime;
        this.destination = destination;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentmethod = paymentmethod;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(int paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
