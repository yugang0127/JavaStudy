package com.jaden.blog.web.servlet;


import com.jaden.blog.domain.Blog;
import com.jaden.blog.service.BlogService;
import com.jaden.blog.service.impl.BlogServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/addBlogServlet")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置编码
        req.setCharacterEncoding("utf-8");
        // 2.获取参数封装对象
        Blog blog = new Blog();
        Map<String, String[]> params = req.getParameterMap();
        try {
            BeanUtils.populate(blog, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3.调用service
        BlogService blogService = new BlogServiceImpl();
        blogService.addBlog(blog);

        // 4.转发
        resp.sendRedirect(req.getContextPath() + "/listBlogServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
