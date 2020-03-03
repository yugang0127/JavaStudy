package com.jaden.blog.service.impl;

import com.jaden.blog.dao.BlogDao;
import com.jaden.blog.dao.impl.BlogDaoImpl;
import com.jaden.blog.domain.Blog;
import com.jaden.blog.service.BlogService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao = new BlogDaoImpl();

    @Override
    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public int addBlog(Blog blog) {
        Date now = new Date();
        blog.setCreateTime(now);
        blog.setUpdateTime(now);
        return blogDao.addBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Blog findBlogById(String id) {
        return blogDao.findBlogById(Integer.parseInt(id));
    }

    @Override
    public List<Blog> findByPage(int start, int pageSize, Map<String, String[]> condition) {
        return blogDao.findByPage(start, pageSize, condition);
    }

    @Override
    public int deleteBlog(String id) {
        return blogDao.deleteBlog(Integer.parseInt(id));
    }

    @Override
    public int deleteSelectedBlog(String[] ids) {
        int count = 0;
        for (String id : ids) {
            count += blogDao.deleteBlog(Integer.parseInt(id));
        }
        return count;
    }
}
