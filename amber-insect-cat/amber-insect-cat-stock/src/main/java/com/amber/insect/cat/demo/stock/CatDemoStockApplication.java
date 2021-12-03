package com.amber.insect.cat.demo.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CatDemoStockApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/3 22:30
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.amber"})
@RestController
public class CatDemoStockApplication {

    /**
     * 提供库存接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/stock")
    public String stock() throws Exception {
        Thread.sleep(200);
        return "stock success";
    }

    public static void main(String[] args) {

        SpringApplication.run(CatDemoStockApplication.class, args);
    }
}
