package com.jaden.javastudy.test;

import com.jaden.javastudy.dao.UserDao;
import com.jaden.javastudy.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("yuwan");
        loginUser.setPassword("123");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
