package com.wangyao.backend.dao;

import com.wangyao.backend.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface UserDao {
    //增
    public int insert(User user);
    //改
    public int update(User user);
    //删
    public int delete(User user);
    //查,通过用户名密码
    public User getUserByLoginMessage(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);//@param具体作用回看
    //查,通过用户ID
    public User getUserById(int id);
    //查所有用户
    public List<User> getAllUser();
    //查,通过用户名称(模糊查找)
    public List<User> getUserByUserName(String userName);
}
