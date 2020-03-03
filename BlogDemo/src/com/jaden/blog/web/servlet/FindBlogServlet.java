package com.jaden.blog.web.servlet;


import com.jaden.blog.domain.Blog;
import com.jaden.blog.service.BlogService;
import com.jaden.blog.service.impl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findBlogServlet")
public class FindBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据
        String id = req.getParameter("id");
        BlogService blogService = new BlogServiceImpl();
        Blog blog = blogService.findBlogById(id);
        req.setAttribute("blog", blog);

        // 2.转发
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
