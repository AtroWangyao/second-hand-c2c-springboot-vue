package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.UserAddress;
import com.wangyao.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService  addressService;

    @RequestMapping("/addCity")
    public String addCity(@RequestBody City city)
    {
        System.out.println(city);
        if(addressService.addCity(city) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/updateCity")
    public String updateCity(@RequestBody City city)
    {
        System.out.println(city);
        if(addressService.updateCity(city) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/deleteCity")
    public String deleteCity(@RequestBody City city)
    {
        System.out.println(city);
        if(addressService.deleteCity(city) == true)
        {
            System.out.println("suc");
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allCity")
    public Object allCity()
    {
        List<City> cityList = addressService.getAllCity();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",cityList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/addUserAddress")
    public String addUserAddress(@RequestBody UserAddress userAddress)
    {
        System.out.println(userAddress);
        if(addressService.addUserAddress(userAddress) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/updateUserAddress")
    public String updateUserAddress(@RequestBody UserAddress userAddress)
    {
        System.out.println(userAddress);
        if(addressService.updateUserAddress(userAddress) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/deleteUserAddress")
    public String deleteUserAddress(@RequestBody UserAddress userAddress)
    {
        System.out.println(userAddress);
        if(addressService.deleteUserAddress(userAddress) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allUserAddressByUserId")
    public Object allUserAddressByUserId(@RequestParam("userId")int userId)
    {
        List<UserAddress> UserAddressList = addressService.getAllUserAddressByUserId(userId);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",UserAddressList);
        return JSON.toJSONString(res);
    }
    
}
