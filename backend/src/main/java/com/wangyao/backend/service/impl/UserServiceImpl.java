package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Certification;
import com.wangyao.backend.bean.User;
import com.wangyao.backend.dao.CertificationDao;
import com.wangyao.backend.dao.UserDao;
import com.wangyao.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CertificationDao certificationDao;
    @Override
    public boolean addUser(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user) > 0;
    }

    @Override
    public boolean deleteUser(User user) {
        return userDao.delete(user) > 0;
    }

    @Override
    public User getUserByLoginMessage(String loginName, String loginPassword) {
        return userDao.getUserByLoginMessage(loginName,loginPassword);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public boolean addCertification(Certification certification) {
        return certificationDao.insert(certification) > 0;
    }

    @Override
    public boolean updateCertification(Certification certification) {
        return certificationDao.update(certification) > 0;
    }

    @Override
    public boolean deleteCertification(Certification certification) {
        return certificationDao.delete(certification) > 0;
    }

    @Override
    public Certification getAllCertificationByUserId(int userId) {
        return certificationDao.getAllCertificationByUserId(userId);
    }
}
