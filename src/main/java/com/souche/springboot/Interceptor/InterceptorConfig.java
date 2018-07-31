package com.souche.springboot.Interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
    @Value("${uaa.url}")
    private String uaaUrl;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(uaaUrl!=null&&!"".equals(uaaUrl)) {
            String[] split = uaaUrl.split(",");
            for (String s : split) {
                //这里可以添加多个拦截器
                registry.addInterceptor(new LoginTimeInterceptor()).addPathPatterns(s);
            }
        }
        super.addInterceptors(registry);
    }
}

