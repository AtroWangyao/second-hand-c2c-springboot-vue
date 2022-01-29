package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.UserAddress;
import com.wangyao.backend.dao.CityDao;
import com.wangyao.backend.dao.UserAddressDao;
import com.wangyao.backend.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
   
    @Autowired
    private CityDao cityDao;
    @Autowired
    private UserAddressDao userAddressDao;
    

    @Override
    public boolean addCity(City city) {
        return cityDao.insert(city) > 0;
    }

    @Override
    public boolean updateCity(City city) {
        return cityDao.update(city) > 0;
    }

    @Override
    public boolean deleteCity(City city) {
        return cityDao.delete(city) > 0;
    }

    @Override
    public City getCityById(int id) {
        return cityDao.getCityById(id);
    }

    @Override
    public List<City> getAllCity() {
        return cityDao.getAllCity();
    }
    @Override
    public boolean addUserAddress(UserAddress userAddress) {
        return userAddressDao.insert(userAddress) > 0;
    }

    @Override
    public boolean updateUserAddress(UserAddress userAddress) {
        return userAddressDao.update(userAddress) > 0;
    }

    @Override
    public boolean deleteUserAddress(UserAddress userAddress) {
        return userAddressDao.delete(userAddress) > 0;
    }

    @Override
    public UserAddress getUserAddressById(int id) {
        return userAddressDao.getUserAddressById(id);
    }
    @Override
    public List<UserAddress> getAllUserAddress() {
        return userAddressDao.getAllUserAddress();
    }

    @Override
    public List<UserAddress> getAllUserAddressByUserId(int userId) {
        return userAddressDao.getAllUserAddressByUserId(userId);
    }


}
