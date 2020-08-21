package com.icbc.cdcp.pssp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PsspInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private PsspInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        // 全部请求都会拦截
        registration.addPathPatterns("/**");
        // 除了/login不拦截
        //registration.excludePathPatterns("/login");
    }
}
