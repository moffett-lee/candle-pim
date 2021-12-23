package com.rainze.cloud.assembly;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;



/**
 *
 * Zipkin 是一个开放源代码分布式的跟踪系统，每个服务向zipkin报告计时数据，zipkin会根据调用关系通过Zipkin UI生成依赖关系图。
 * Zipkin提供了可插拔数据存储方式：In-Memory、MySql、Cassandra以及Elasticsearch。为了方便在开发环境我直接采用了In-Memory方式进行存储，生产数据量大的情况则推荐使用Elasticsearch。
 * Span：基本工作单元，例如，在一个新建的span中发送一个RPC等同于发送一个回应请求给RPC，span通过一个64位ID唯一标识，trace以另一个64位ID表示，span还有其他数据信息，比如摘要、时间戳事件、关键值注释(tags)、span的ID、以及进度ID(通常是IP地址)
 * span在不断的启动和停止，同时记录了时间信息，当你创建了一个span，你必须在未来的某个时刻停止它。
 * Trace：一系列spans组成的一个树状结构，例如，如果你正在跑一个分布式大数据工程，你可能需要创建一个trace。
 * Annotation：用来及时记录一个事件的存在，一些核心annotations用来定义一个请求的开始和结束
 *
 * 在spring Cloud为Finchley版本时，如果只需要默认的实现，则不需要自己构建Zipkin Server了，只需要下载jar即可
 * 本项目已经下载好 解压运行即可
 *
 *  解压命令  java -jar zipkin-server-2.12.2-exec.jar --logging.level.zipkin2=INFO
 *  http://127.0.0.1:9411
 *
 * @Author: liyuze
 * @Date: 2020/4/30 16:16
 */
@EnableDiscoveryClient
@EnableZipkinServer //开启zpk服务
@SpringBootApplication
public class RainzeSpringCloudZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainzeSpringCloudZipkinApplication.class, args);
    }

}
