package com.jaden.blog.web.servlet;


import com.jaden.blog.dao.UserDao;
import com.jaden.blog.dao.impl.UserDaoImpl;
import com.jaden.blog.domain.User;
import com.jaden.blog.service.UserService;
import com.jaden.blog.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置编码
        req.setCharacterEncoding("utf-8");
        // 2.获取参数
        Map<String, String[]> params = req.getParameterMap();
        User loginUser = new User();
        String verifycode = req.getParameter("verifycode");

        // 验证checkCode
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("CHECKCODE_SERVER");
        if (verifycode == null && !verifycode.equalsIgnoreCase(checkCodeSession)) {
            req.setAttribute("errorMsg", "验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        // 3.使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 4.调用UserService
        UserService userService = new UserServiceImpl();
        User user = userService.login(loginUser);
        // 5.判断
        if (user != null) {
            req.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("errorMsg", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
