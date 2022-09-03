package com.amber.insect.cat.demo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CatDemoAccountApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/3 22:13
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.amber"})
@RestController
public class CatDemoAccountApplication {

    /**
     * 提供账户服务接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/account")
    public String account() throws Exception {
        Thread.sleep(150);
        return "account success";
    }


    public static void main(String[] args) {
        SpringApplication.run(CatDemoAccountApplication.class, args);
    }
}
