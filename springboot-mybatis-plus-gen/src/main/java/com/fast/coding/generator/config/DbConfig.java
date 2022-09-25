package com.fast.coding.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源相关信息
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DbConfig {

    /**
     * 驱动
     */
    private String driverClassName;

    /**
     * 连接账号
     */
    private String userName;

    /**
     * 连接密码
     */
    private String password;

    /**
     * JDBC url
     */
    private String url;

}
