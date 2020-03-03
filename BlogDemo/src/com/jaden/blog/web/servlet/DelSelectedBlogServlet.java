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

@WebServlet("/delSelectedBlogServlet")
public class DelSelectedBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据
        String[] bids = req.getParameterValues("bid");
        // 2.调用服务
        BlogService blogService = new BlogServiceImpl();
        blogService.deleteSelectedBlog(bids);

        // 3.转发
        resp.sendRedirect(req.getContextPath() + "/listBlogServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
