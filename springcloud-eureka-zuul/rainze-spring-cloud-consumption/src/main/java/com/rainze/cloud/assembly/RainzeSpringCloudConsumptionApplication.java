package com.rainze.cloud.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class RainzeSpringCloudConsumptionApplication {

    public static void main(String[] args) {
        System.out.println("================================================== 开始启动 消费者应用shop =============================================================");
        System.out.println("请在控制台指定shop应用的端口号 —— [端口号随意指定，注意不要与本机端口号出现冲突即可]");

        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(RainzeSpringCloudConsumptionApplication.class).properties("server.port=" + port).run(args);//启动项目

        System.out.println("================================================== 消费者应用shop 启动成功 =============================================================");
        //SpringApplication.run(ShopApplication.class,args);

        /**
         * 这样也可以启动springboot应用；
         *      其实SpringApplicationBuilder只是对SpringApplication的启动进行了封装而已；
         *      看源码就知道了，最终还是调用的SpringApplication.run方法；
         */
        //new SpringApplicationBuilder(ShopApplication.class).run(args);
    }
}
