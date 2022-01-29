package com.wangyao.backend.service;

import com.wangyao.backend.bean.Certification;
import com.wangyao.backend.bean.User;
import com.wangyao.backend.dao.UserDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //增
    public boolean addUser(User user);
    //改
    public boolean updateUser(User user);
    //删
    public boolean deleteUser(User user);
    //查,通过用户名密码
    public User getUserByLoginMessage(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);//@param具体作用回看
    //查,通过用户ID
    public User getUserById(int id);
    //查所有用户
    public List<User> getAllUser();
    //查,通过用户名称(模糊查找)
    public List<User> getUserByUserName(String userName);
    //增验证
    public boolean addCertification(Certification certification);
    //改验证
    public boolean updateCertification(Certification certification);
    //删验证
    public boolean deleteCertification(Certification certification);
    //查该用户下的验证
    public Certification getAllCertificationByUserId(int userId);

}
