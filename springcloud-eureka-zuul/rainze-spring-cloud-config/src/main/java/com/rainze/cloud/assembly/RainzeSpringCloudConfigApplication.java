package com.rainze.cloud.assembly;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

/**
 *
 *
 * 启动项目 分别能看到三种不同格式得yml配置文件内容
 * 1、properties 2、yml 3、json  并且可以相互转换
 * 如果配置文件的内容格式有问题的话，访问会报500错误。我们可以利用这个特性，来检查我们的配置文件是否正确
 * 访问的配置文件名后面还有一个-a，这其实是config的访问规则。后面必须要跟个-xxx，所以在创建文件的时候，最好是按这种命名规则来创建
 * 配置文件的访问规则如下：
 * /{name}-{profiles}.yml
 * /{label}/{name}-{profiles}.yml
 * name : 文件名，一般以服务名来命名
 * profiles : 一般作为环境标识
 * lable : 分支（branch），指定访问某分支下的配置文件
 *
 * 有一点值得注意的是，如果有两个前缀名相同文件，例如一个order.yml，一个order-dev.yml。那么在访问相同前缀的文件时，config-server会对这两个文件进行一个合并。例如order.yml有一
 * 段配置是order-dev.yml没有的，理应访问order-dev.yml的时候是没有那段配置的，但访问的结果却是它俩合并之后的内容，即order-dev.yml会拥有order.yml里所配置的内容

 * @Author: liyuze
 * @Date: 2020/4/29 11:40
 */
@EnableConfigServer //开启配置中心服务
@EnableEurekaClient
@SpringBootApplication
public class RainzeSpringCloudConfigApplication {

    public static void main(String[] args) {
        System.out.println("================================================== 开始启动 Config Server配置中心服务 =============================================================");
        System.out.println("请在控制台指定Config Server配置中心服务的端口号 —— [端口号随意指定，注意不要与本机端口号出现冲突即可]");
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(RainzeSpringCloudConfigApplication.class).properties("server.port=" + port).run(args);//启动项目

        System.out.println("================================================== Config Server配置中心服务启动成功 =============================================================");

    }
}

