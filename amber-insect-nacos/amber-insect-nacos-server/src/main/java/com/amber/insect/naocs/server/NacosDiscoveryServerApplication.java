package com.amber.insect.naocs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @ClassName NacosDiscoveryServerApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/10 23:09
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryServerApplication.class, args);
    }

    /**
     * 获取股票价格接口
     * @param name
     * @return
     */
    @RequestMapping("/getPrice")
    public String getPrice(@RequestParam(defaultValue = "中国平安") String name) {
        return "股票名称：" + name + ", 股票价格：" + (new Random().nextInt(100-20)+20);
    }
}
