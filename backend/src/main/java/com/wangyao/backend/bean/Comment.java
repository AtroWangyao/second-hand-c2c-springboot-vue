package com.wangyao.backend.bean;

import java.util.Date;

public class Comment {
    private int id;
    private int goodId;
    private double userId;
    private String text;
    private int score;
    private Date date;
    private int type;
    //附加
    private User user;//里面包括username 和 photo
    public Comment(int id, int goodId, double userId, String text, int score, Date date, int type) {
        this.id = id;
        this.goodId = goodId;
        this.userId = userId;
        this.text = text;
        this.score = score;
        this.date = date;
        this.type = type;
    }

    public Comment(int id, int goodId, double userId, String text, int score, Date date, int type, User user) {
        this.id = id;
        this.goodId = goodId;
        this.userId = userId;
        this.text = text;
        this.score = score;
        this.date = date;
        this.type = type;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", score=" + score +
                ", date=" + date +
                ", user=" + user +
                ", type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
