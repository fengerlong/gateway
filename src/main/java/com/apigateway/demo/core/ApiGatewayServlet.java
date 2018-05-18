package com.apigateway.demo.core;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiGatewayServlet extends HttpServlet {

    private static final long serialVersionUID = -3080094882178168124L;

    private ApplicationContext applicationContext;

    private ApiGatewayHandler apiGatewayHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        //获取应用上下文
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        //通过上下文拿到网关处理器
        apiGatewayHandler = applicationContext.getBean(ApiGatewayHandler.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiGatewayHandler.handle(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiGatewayHandler.handle(req,resp);
    }
}
