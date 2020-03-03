package com.jaden.blog.dao;

import com.jaden.blog.domain.Blog;
import com.jaden.blog.domain.User;
import com.jaden.blog.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 操作数据库中Blog表的类
 */
public interface BlogDao {


    List<Blog> findAll();

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    Blog findBlogById(int id);

    int countBy(Map<String, String[]> condition);

    List<Blog> findByPage(int start, int pageSize, Map<String, String[]> condition);

    int deleteBlog(int id);
}
