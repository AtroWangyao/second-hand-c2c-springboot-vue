package com.wangyao.backend.dao;

import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.Good;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface CityDao {
    //增
    public int insert(City city);
    //改
    public int update(City city);
    //删
    public int delete(City city);
    //查,通过ID
    public City getCityById(int id);
    //查所有
    public List<City> getAllCity();
}
