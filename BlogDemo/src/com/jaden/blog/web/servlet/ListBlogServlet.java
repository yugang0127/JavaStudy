package com.jaden.blog.web.servlet;


import com.jaden.blog.dao.BlogDao;
import com.jaden.blog.dao.UserDao;
import com.jaden.blog.dao.impl.BlogDaoImpl;
import com.jaden.blog.dao.impl.UserDaoImpl;
import com.jaden.blog.domain.Blog;
import com.jaden.blog.domain.User;
import com.jaden.blog.service.BlogService;
import com.jaden.blog.service.impl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/listBlogServlet")
public class ListBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据
        BlogService blogService = new BlogServiceImpl();
        List<Blog> blogs = blogService.findAll();
        req.setAttribute("blogs", blogs);

        // 2.转发
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
