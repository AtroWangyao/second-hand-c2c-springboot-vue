package com.wangyao.backend.bean;

public class Collection {
    private int id;
    private int goodId;
    private double userId;

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

    public Collection(int id, int goodId, double userId) {
        this.id = id;
        this.goodId = goodId;
        this.userId = userId;
    }

    public Collection() {
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", userId=" + userId +
                '}';
    }
}
