package com.jaden.blog.service;

import com.jaden.blog.domain.User;

public interface UserService {
    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据,没有查询到，返回null
     */
    User login(User loginUser);
}
