package com.jaden.blog.dao.impl;

import com.jaden.blog.dao.BlogDao;
import com.jaden.blog.domain.Blog;
import com.jaden.blog.domain.User;
import com.jaden.blog.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class BlogDaoImpl implements BlogDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Blog> findAll() {
        try {
            String sql = "select * from blog ";
            List<Blog> blogs = template.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
            return blogs;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addBlog(Blog blog) {
        String sql = "insert into blog values(null, ?, ?, ?, ?, ?, ?)";
        Date now = new Date();
        return template.update(sql, blog.getTitle(), blog.getSummary(), blog.getContent(), blog.getType(), now, now);
    }

    @Override
    public int updateBlog(Blog blog) {
        String sql = "update blog set title=?, summary=?, content=?, type=? where id=?";
        return template.update(sql, blog.getTitle(), blog.getSummary(), blog.getContent(), blog.getType(), blog.getId());
    }

    @Override
    public Blog findBlogById(int id) {
        String sql = "select * from blog where id = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Blog>(Blog.class), id);
    }

    @Override
    public int countBy(Map<String, String[]> condition) {
        // 定义模板sql
        String sql = "select count(*) from blog where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        // 遍历map获取参数
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页参数
            if ("pageNum".equals(key) || "pageSize".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Blog> findByPage(int start, int pageSize, Map<String, String[]> condition) {
        // 定义模板sql
        String sql = "select * from blog where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        // 遍历map获取参数
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页参数
            if ("pageNum".equals(key) || "pageSize".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();

        return template.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class), params.toArray());
    }

    @Override
    public int deleteBlog(int id) {
        String sql = "delete from blog where id = ? ";
        return template.update(sql, id);
    }
}
