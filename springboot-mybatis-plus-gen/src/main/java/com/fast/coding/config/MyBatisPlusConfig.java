package com.fast.coding.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus配置类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * mybatis-plus分页插件
     * @return {PaginationInterceptor}
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
