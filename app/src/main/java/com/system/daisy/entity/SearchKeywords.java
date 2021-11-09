package com.system.daisy.entity;

public class SearchKeywords {
    private String keyword;
    private int times;

    public SearchKeywords() {
    }

    public SearchKeywords(String keyword, int times) {
        this.keyword = keyword;
        this.times = times;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
