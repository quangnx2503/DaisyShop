package com.system.daisy.entity;

public class User {
    private String  email;
    private String pass_word;
    private String name;
    private boolean gender;
    private String phone;
    private String dob;

    public User() {
    }

    public User(String email, String pass_word, String name, boolean gender, String phone, String dob) {
        this.email = email;
        this.pass_word = pass_word;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
