package com.amber.insect.nacos.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName NacosDiscoveryClientApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/10 23:08
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosDiscoveryClientApplication {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 声明RestTemplate， 支持负载均衡
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryClientApplication.class, args);
    }

    /**
     * 客户端调用接口
     * @param name
     * @return
     */
    @RequestMapping("/client")
    public String client(@RequestParam String name) {
        return restTemplate.getForObject("http://nacos-discovery-server/getPrice?name=" + name, String.class);
    }

}
