package com.wangyao.backend.bean;

import java.util.Date;
import java.util.List;

public class Good {
    private int id;
    private int categoryId;
    private String name;
    private double price;
    private double sprice;
    private String desc;
    private String manufacturer;
    private Date purchaseDate;
    private Date saleDate;
    private int appearanceValue;
    private int functionValue;
    private int packageValue;
    private int dealType;
    private int userId;
    //附加
    private List<Tag> tagList;
    private List<Picture> pictureList;
    private User user;

    public Good(int id, int categoryId, String name, double price, double sprice, String desc, String manufacturer, Date purchaseDate, Date saleDate, int appearanceValue, int functionValue, int packageValue, int dealType, int userId, List<Tag> tagList, List<Picture> pictureList, User user) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.sprice = sprice;
        this.desc = desc;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.appearanceValue = appearanceValue;
        this.functionValue = functionValue;
        this.packageValue = packageValue;
        this.dealType = dealType;
        this.userId = userId;
        this.tagList = tagList;
        this.pictureList = pictureList;
        this.user = user;
    }

    public Good() {
    }

    public Good(int id, int categoryId, String name, double price, double sprice, String desc, String manufacturer, Date purchaseDate, Date saleDate, int appearanceValue, int functionValue, int packageValue, int dealType, int userId) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.sprice = sprice;
        this.desc = desc;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.appearanceValue = appearanceValue;
        this.functionValue = functionValue;
        this.packageValue = packageValue;
        this.dealType = dealType;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Good(int id, int categoryId, String name, double price, double sprice, String desc, String manufacturer, Date purchaseDate, Date saleDate, int appearanceValue, int functionValue, int packageValue, int dealType, int userId, List<Tag> tagList, List<Picture> pictureList) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.sprice = sprice;
        this.desc = desc;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.appearanceValue = appearanceValue;
        this.functionValue = functionValue;
        this.packageValue = packageValue;
        this.dealType = dealType;
        this.userId = userId;
        this.tagList = tagList;
        this.pictureList = pictureList;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSprice() {
        return sprice;
    }

    public void setSprice(double sprice) {
        this.sprice = sprice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getAppearanceValue() {
        return appearanceValue;
    }

    public void setAppearanceValue(int appearanceValue) {
        this.appearanceValue = appearanceValue;
    }

    public int getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(int functionValue) {
        this.functionValue = functionValue;
    }

    public int getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(int packageValue) {
        this.packageValue = packageValue;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sprice=" + sprice +
                ", desc='" + desc + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", saleDate=" + saleDate +
                ", appearanceValue=" + appearanceValue +
                ", functionValue=" + functionValue +
                ", packageValue=" + packageValue +
                ", dealType=" + dealType +
                ", userId=" + userId +
                ", tagList=" + tagList +
                ", pictureList=" + pictureList +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
