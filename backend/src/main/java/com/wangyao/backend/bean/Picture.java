package com.wangyao.backend.bean;

public class Picture {
    private int id;
    private String src;
    private int type;

    public Picture(int id, String src, int type) {
        this.id = id;
        this.src = src;
        this.type = type;
    }
    public Picture(String src, int type) {
        this.src = src;
        this.type = type;
    }
    public Picture(int id) {
        this.id = id;
    }

    public Picture() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", type=" + type +
                '}';
    }
}
