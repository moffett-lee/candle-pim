package com.amber.insect.cat.demo.gateway;

import com.alidaodao.app.Cat;
import com.alidaodao.app.CatConstants;
import com.alidaodao.app.message.Transaction;
import com.amber.insect.cat.demo.gateway.utils.CatRestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @ClassName com.amber.insect.cat.demo.gateway.CatDemoGatewayApplication
 * @Description 网关启动器
 * @Author Amber.L
 * @Date 2021/12/2 22:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.amber"})
@RestController
public class CatDemoGatewayApplication {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 指向订单服务的接口
     */
    @Value("${service2.address:localhost:8082}")


    private String serviceAddress;
    public static void main(String[] args) {
        SpringApplication.run(CatDemoGatewayApplication.class,args);
    }



    /**
     * @Author amber.liyuze
     * @Description 正常请求
     * @Date 2021/12/2 23:42
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping("/gateway")
    public String gateway() throws Exception {
        Thread.sleep(100);
        String response = restTemplate.getForObject("http://" + serviceAddress + "/order", String.class);
        return "gateway response ==> " + response;
    }

    /**
     * @Author amber.liyuze
     * @Description 错误请求
     * @Date 2021/12/2 23:43
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping("/timeout")
    public String timeout() throws Exception {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_URL, "timeout");
        try {
            Thread.sleep(100);
            String response = restTemplate.getForObject("http://" + serviceAddress + "/timeout", String.class);
            return response;
        }catch(Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        }finally {
            t.complete();
        }

    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }







}
