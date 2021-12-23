package com.rainze.cloud.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Dashboard仪表盘
 * #### Hystrix Dashboard仪表盘
 * 将HystrixDashboard仪表盘单独作为一个模块，目的还是为了让微服务结构清晰。监控只是一个附属功能，与具体的业务功能模块应该独立开来，方便部署维护、不会与业务功能模块产生额外的系统开销。
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}
