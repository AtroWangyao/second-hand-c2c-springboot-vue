package com.wangyao.backend.service;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.Tag;
import com.wangyao.backend.bean.UserAddress;

import java.util.List;

public interface AddressService {
    //增商品
    public boolean addCity(City city);
    //改商品
    public boolean updateCity(City city);
    //删商品
    public boolean deleteCity(City city);
    //查商品,通过商品ID
    public City getCityById(int id);
    //查所有商品
    public List<City> getAllCity();

    //增用户地址
    public boolean addUserAddress(UserAddress userAddress);
    //改用户地址
    public boolean updateUserAddress(UserAddress userAddress);
    //删用户地址
    public boolean deleteUserAddress(UserAddress userAddress);
    //查商品,通过商品ID
    public UserAddress getUserAddressById(int id);
    //查所有用户地址
    List<UserAddress> getAllUserAddress();

    List<UserAddress> getAllUserAddressByUserId(int userId);
}
