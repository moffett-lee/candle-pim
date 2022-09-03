package com.chonger.redis.queue.subscribe.listener;

import com.chonger.redis.queue.common.constant.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisPublishSubscribeConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisPublishSubscribeConfig.class);

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        // 添加订阅者监听类，数量不限.PatternTopic定义监听主题,这里监听test-topic主题
        logger.info("add listener topic is {}", RedisConstant.TOPIC_PRAISE);
        container.addMessageListener(new SubscribeListener(), new PatternTopic(RedisConstant.TOPIC_PRAISE));
        return container;
    }

}
