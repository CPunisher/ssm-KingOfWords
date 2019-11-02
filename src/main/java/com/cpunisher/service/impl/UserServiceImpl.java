package com.cpunisher.service.impl;

import com.cpunisher.dao.UserDao;
import com.cpunisher.model.User;
import com.cpunisher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(String openId, String password, String nickname, String iconUrl) {
        User user = userDao.getUserByOpenId(openId);
        if (user == null) {
            userDao.register(openId, password, nickname, iconUrl);
        }
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByOpenId(String openId) {
        return userDao.getUserByOpenId(openId);
    }

    @Override
    public User validateUser(String openId, String password) {
        return userDao.validateUser(openId, password);
    }
}
