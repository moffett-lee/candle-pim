package com.rainze.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author LYZ
 * @version 1.0
 * @description  使用 spring 的 Schedule 建立定时任务
 * @projectName rainze-springboot-websocket
 * @data 2020/5/5 11:03
 */
@EnableScheduling
@Component
public class TimeTask {

    /**
     *  @EnableScheduling 开启 spring 定时任务功能
     *  @Scheduled(cron = "0/10 * * * * ?") 用于标识定时执行的方法，此处主要方法返回值一定是 void，没有入参。
     * 对应定时时间配置可以百度 cron 语法，根据自己的业务选择合适的周期
     * 在这类中，我们通过上面 MyWebSocket 提供的静态方法获取其中的 webSocketSet ，来获取所有此业务相关的所有 websocketsession，
     * 可以在定时任务中对 session 内容进行验证判断（权限验证等），进行发送消息
     */

    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);
    @Scheduled(cron = "0/1 * * * * ?")
    public void task(){
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<MyWebSocket> webSocketSet =
                MyWebSocket.getWebSocketSet();
        int i = 0 ;
        webSocketSet.forEach(c->{
            try {
                c.sendMessage("  定时发送  " + new Date().toLocaleString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.err.println("/n 定时任务完成.......");
    }
}
