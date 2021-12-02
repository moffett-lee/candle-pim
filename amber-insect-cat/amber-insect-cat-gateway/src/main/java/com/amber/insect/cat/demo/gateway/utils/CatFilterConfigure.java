package com.amber.insect.cat.demo.gateway.utils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author amber.liyuze
 * @Description 
 * @Date 2021/12/2 23:49
 * @Param  * @param null
 * @Return 
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
