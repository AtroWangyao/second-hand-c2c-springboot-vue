package com.wangyao.backend.dao;

import com.wangyao.backend.bean.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface UserAddressDao {
    //增
    public int insert(UserAddress userAddress);
    //改
    public int update(UserAddress userAddress);
    //删
    public int delete(UserAddress userAddress);
    //查,通过ID
    public UserAddress getUserAddressById(int id);
    //查所有
    public List<UserAddress> getAllUserAddress();

    List<UserAddress> getAllUserAddressByUserId(int userId);
}
