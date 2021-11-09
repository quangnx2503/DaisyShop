package com.system.daisy.entity;

import java.util.Date;

public class SearchHistories {
    private String keyword;
    private String email;
    private Date lastTime;

    public SearchHistories() {
    }

    public SearchHistories(String keyword, String email, Date lastTime) {
        this.keyword = keyword;
        this.email = email;
        this.lastTime = lastTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
