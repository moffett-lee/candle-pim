package com.amber.insect.hateoas.order;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HateoasOrderApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/9 20:47
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.amber"})
@EntityScan(basePackages = {"com.amber"})
@EnableJpaRepositories(basePackages = {"com.amber"})
public class HateoasOrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(HateoasOrderApplication.class, args);
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

    /**
     * 设置HTTP连接池参数
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                //  Keep-Alive 策略
                .setKeepAliveStrategy(new RemoteConnectionKeepAliveStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    /**
     * 设置RestTemplate参数
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(2000))
                .setReadTimeout(Duration.ofMillis(1800))
                .requestFactory(this::requestFactory)
                .build();
    }
}
