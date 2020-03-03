package com.jaden.blog.service;

import com.jaden.blog.domain.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> findAll();

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    Blog findBlogById(String id);

    List<Blog> findByPage(int start, int pageSize, Map<String, String[]> condition);

    int deleteBlog(String id);

    int deleteSelectedBlog(String[] ids);

}
