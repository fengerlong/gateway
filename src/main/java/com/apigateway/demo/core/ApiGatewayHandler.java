package com.apigateway.demo.core;

import org.hibernate.validator.internal.engine.messageinterpolation.ParameterTermResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiGatewayHandler implements ApplicationContextAware,InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(ApiGatewayHandler.class);

    private static final String METHOD = "method";

    private static final String PARAMS = "params";

    ApiStore apiStore;

    final ParamterNameDiscoverer

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) {
//        String params = req.getParameter();
    }
}
