package org.liko.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("我房间开始放大就看懂两地分居");
        PrintWriter writer = response.getWriter();
        writer.print("我房间开始放大就看懂两地分居");
        writer.flush();

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("我房间开始放大就看懂两地分居");
    }
}
