package com.amber.insect.stocks.demo;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @Author amber.liyuze
 * @Description
 * @Date 2021/12/9 21:00
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.amber"})
@EntityScan(basePackages = {"com.amber"})
@EnableJpaRepositories(basePackages = {"com.amber"})
public class HateaoasStocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateaoasStocksApplication.class, args);
    }

    /**
     * Hibernate的初始化
     * @return
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * 用于json数据的封装处理
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return builder -> {
            builder.indentOutput(true);
        };
    }
}
