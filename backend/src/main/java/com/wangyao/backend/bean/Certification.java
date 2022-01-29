package com.wangyao.backend.bean;

public class Certification {
    private int id;
    private int userId;
    private int status;
    private String picture1;
    private String picture2;
    private String message;
    private String idCardNumber;
    private String realName;


    public Certification(int id, int userId, int status, String picture1, String picture2, String message, String idCardNumber, String realName) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.message = message;
        this.idCardNumber = idCardNumber;
        this.realName = realName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", message='" + message + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", realName='" + realName + '\'' +
                '}';
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Certification() {
    }

}
