package com.wangyao.backend.bean;

public class Recommend {
    private int id;
    private int goodId;
    private int type;

    @Override
    public String toString() {
        return "Recommend{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", type=" + type +
                '}';
    }

    public Recommend() {
    }

    public Recommend(int id, int goodId, int type) {
        this.id = id;
        this.goodId = goodId;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
