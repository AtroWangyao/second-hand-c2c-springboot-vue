package com.wangyao.backend.bean;

public class UserAddress {
    private int id;
    private int userId;
    private int cityId;
    private String detail;
    private String name;
    private int type;

    public UserAddress(int id, int userId, int cityId, String detail, String name, int type) {
        this.id = id;
        this.userId = userId;
        this.cityId = cityId;
        this.detail = detail;
        this.name = name;
        this.type = type;
    }

    public UserAddress() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "userAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", cityId=" + cityId +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
