package com.apigateway.demo.config;

import com.apigateway.demo.core.ApiGatewayServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new ApiGatewayServlet(),"/*");
    }
}
