package com.system.daisy.entity;

public class Comment {
    private String question;
    private String answer;
    private String email;


    public Comment() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Comment(String question, String answer,String email) {
        this.question = question;
        this.answer = answer;this.email = email;
    }

    public Comment(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
