package com.system.daisy.entity;

import java.util.Date;

public class Notifications {
    private int id;
    private String email;
    private Date time;
    private String content;
    private String title;

    public Notifications() {
    }

    public Notifications(int id, String email, Date time, String content, String title) {
        this.id = id;
        this.email = email;
        this.time = time;
        this.content = content;
        this.title = title;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
