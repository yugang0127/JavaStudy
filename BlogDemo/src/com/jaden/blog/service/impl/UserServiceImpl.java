package com.jaden.blog.service.impl;

import com.jaden.blog.dao.UserDao;
import com.jaden.blog.dao.impl.UserDaoImpl;
import com.jaden.blog.domain.User;
import com.jaden.blog.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User loginUser) {
        return userDao.login(loginUser);
    }
}
