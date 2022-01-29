package com.wangyao.backend.bean;

import java.util.Date;

public class Order {
    private int id;
    private String number;
    private int sellerId;
    private int buyerId;
    private int dealType;
    private Date date;
    private int status;
    private int userAddressId;
    private double money;
    private int goodId;
    private Date offlineDealDate;
    //附加
    private Good good; //订单买的good
    private UserAddress userAddress; //订单的收货地址

    public int getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Order(int id, String number, int sellerId, int buyerId, int dealType, Date date, int status, int userAddressId, double money, int goodId, Date offlineDealDate, Good good, UserAddress userAddress) {
        this.id = id;
        this.number = number;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.dealType = dealType;
        this.date = date;
        this.status = status;
        this.userAddressId = userAddressId;
        this.money = money;
        this.goodId = goodId;
        this.offlineDealDate = offlineDealDate;
        this.good = good;
        this.userAddress = userAddress;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order(int id, String number, int sellerId, int buyerId, int dealType, Date date, int status, int userAddressId, double money, int goodId, Date offlineDealDate) {
        this.id = id;
        this.number = number;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.dealType = dealType;
        this.date = date;
        this.status = status;
        this.userAddressId = userAddressId;
        this.money = money;
        this.goodId = goodId;
        this.offlineDealDate = offlineDealDate;
    }

    public Order() {

    }




    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", dealType=" + dealType +
                ", date=" + date +
                ", status=" + status +

                ", money=" + money +
                ", good=" + good +
                ", userAddress=" + userAddress +
                ", offlineDealDate=" + offlineDealDate +
                '}';
    }

    public Date getOfflineDealDate() {
        return offlineDealDate;
    }

    public void setOfflineDealDate(Date offlineDealDate) {
        this.offlineDealDate = offlineDealDate;
    }
}
