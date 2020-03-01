package com.jaden.javastudy.web.servlet;

import com.jaden.javastudy.dao.UserDao;
import com.jaden.javastudy.domain.User;
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        // 验证checkCode
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCodeSession");
        if (checkCode != null && checkCode.equalsIgnoreCase(checkCodeSession)) {
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            // 3.使用BeanUtils封装
//        try {
//            BeanUtils.populate(loginUser, params);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
            // 4.调用UserDao
            UserDao userDao = new UserDao();
            User user = userDao.login(loginUser);
            // 5.判断
            if (user != null) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/success.jsp").forward(req, resp);
            } else {
                req.setAttribute("loginError", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("ccError", "验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
