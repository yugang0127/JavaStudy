package com.jaden.blog.test;

import com.jaden.blog.dao.UserDao;
import com.jaden.blog.dao.impl.UserDaoImpl;
import com.jaden.blog.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("yuwan");
        loginUser.setPassword("123");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
