package com.jaden.javastudy.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 1. 有：不是第一次访问
    1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
    2. 写回Cookie：lastTime=2018年6月10日11:50:01
 2. 没有：是第一次访问
    1. 响应数据：您好，欢迎您首次访问
    2. 写回Cookie：lastTime=2018年6月10日11:50:01


 */
@WebServlet("/cookieServlet")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        boolean flag = false;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals("lastTime")) {
                flag = true;
                //设置Cookie的value
                //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
                String value = cookie.getValue();
                Date now  = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String strDate = sdf.format(now);
                System.out.println("编码前：" + strDate);
                strDate = URLEncoder.encode(strDate, "utf-8");
                System.out.println("编码后：" + strDate);
                cookie.setValue(strDate);
                cookie.setMaxAge(60 * 60 * 24);
                resp.addCookie(cookie);

                System.out.println("解码前：" + value);
                value = URLDecoder.decode(value, "utf-8");
                System.out.println("解码后：" + value);
                resp.getWriter().write("<h1>欢迎回来，您上次访问时间为:"+value+"</h1>");
                break;
            }
        }

        if (!flag) {
            //设置Cookie的value
            //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
            Date now  = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String strDate = sdf.format(now);
            System.out.println("编码前：" + strDate);
            strDate = URLEncoder.encode(strDate, "utf-8");
            System.out.println("编码后：" + strDate);
            Cookie cookie = new Cookie("lastTime", strDate);
            cookie.setValue(strDate);
            cookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(cookie);

            resp.getWriter().write("<h1>您好，欢迎您首次访问</h1>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
