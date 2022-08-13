package com.lu.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/*
* 编写servlet映射，就是在web.xml中编写
* 为什么要映射，我们编写的是java程序，需要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务中注册我们写的servlet
* 还需要给他一个浏览器能够访问的路径*/
public class HelloServlet extends HttpServlet {
    //由于get或者post只是请求实现不同的方式，可以相互调用，因为业务逻辑一样
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter();//响应流
        writer.println("hello,servlet");
        //在java目录下新建properties和在resources目录下新建properties 都被打包在同一个路径下面 classpath
        InputStream resourceAsStream = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String username = properties.getProperty("username");
        String passward = properties.getProperty("password");
        resp.getWriter().println(username+""+passward);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
