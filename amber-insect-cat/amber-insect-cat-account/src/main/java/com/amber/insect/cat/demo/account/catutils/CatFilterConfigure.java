package com.amber.insect.cat.demo.account.catutils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
@Configuration
public class CatFilterConfigure {

    /**
     * CAT 过滤器的注册配置
     * @return
     */
    @Bean
    public FilterRegistrationBean catFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CatServletFilter filter = new CatServletFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }
}
