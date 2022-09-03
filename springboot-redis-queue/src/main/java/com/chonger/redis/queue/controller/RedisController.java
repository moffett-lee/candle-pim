package com.chonger.redis.queue.controller;


import com.chonger.redis.queue.publish.service.PublisherRedisMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private PublisherRedisMessage publisherRedisMessage;



    @GetMapping(value = "/redis")
    public String secKill(){
        //发布消息
        publisherRedisMessage.pubMessage();
        return "success";

    }




}
