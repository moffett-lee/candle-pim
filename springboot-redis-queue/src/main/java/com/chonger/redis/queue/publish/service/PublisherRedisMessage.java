package com.chonger.redis.queue.publish.service;

import com.chonger.redis.queue.common.constant.RedisConstant;
import com.chonger.redis.queue.common.pojo.UserInfo;
import com.chonger.redis.queue.util.RedisPubUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherRedisMessage {
    private static final Logger log = LoggerFactory.getLogger(PublisherRedisMessage.class);

    @Autowired
    private RedisPubUtils redisUtils;

    //@Scheduled(fixedRate = 5000)
    public void pubMessage() {
        UserInfo user = new UserInfo(1, "何同学", 26, "男", "重庆市xxx区xxx县xxx镇");
        redisUtils.publish(RedisConstant.TOPIC_PRAISE, user);
        log.info("业务消息发布成功! 消息体:{}", user);
    }
}
