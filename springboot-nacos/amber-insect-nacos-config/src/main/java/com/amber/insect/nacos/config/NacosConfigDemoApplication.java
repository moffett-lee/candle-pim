package com.amber.insect.nacos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NacosConfigDemoApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/10 22:59
 * @Version 1.0
 **/
@SpringBootApplication
@RestController    // 动态配置才能生效。
@RefreshScope
public class NacosConfigDemoApplication {
    @Value(value = "${stockName:中国平安}")
    private String stockName;

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigDemoApplication.class, args);
    }

    /**
     * 获取股票名称接口
     * @return
     */
    @RequestMapping("/getStock")
    public String getStock() {
        return "股票名称：" + stockName;
    }

}
