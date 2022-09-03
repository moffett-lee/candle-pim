package com.chonger.redis.queue.subscribe.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 定义多个的
 */
@Component
public class RedisAllReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(RedisAllReceiver.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        Object itemValue = redisTemplate.getValueSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        logger.info("topic_*: {}, value: {}", topic, itemValue);
    }

//    @Override
//    public void handleMessage(String message) {
//        System.out.println("String");
//        System.out.println(message);
//    }
//
//    @Override
//    public void handleMessage(Map message) {
//        System.out.println("Map");
//        System.out.println(message);
//    }
//
//    @Override
//    public void handleMessage(byte[] message) {
//        System.out.println("byte[]");
//        System.out.println(message);
//    }

//    @Override
//    public void handleMessage(Serializable message) {
//        System.out.println("Serializable");
//        System.out.println(message);
//    }

//    @Override
//    public void handleMessage(Serializable message, String channel) {
//        System.out.println("Serializable-channel");
//        System.out.println(message);
//    }
}
