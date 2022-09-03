package com.chonger.redis.queue.subscribe.listener;

import com.chonger.redis.queue.common.constant.RedisConstant;
import com.chonger.redis.queue.subscribe.receiver.ReceiverRedisMessage;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.*;

/**
 * 配置消息监听
 */
@Configuration
public class MessageListenerConfig {
    /**
     * 创建连接工厂  初始化监听器
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //接收消息的主题   可以定义多个
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisConstant.TOPIC_PRAISE));
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisConstant.TOPIC_ONE));

        // 匹配多个  channel
        container.addMessageListener(listenerAdapter, new PatternTopic("topic_*"));

        /**
         * 如果不定义线程池，每一次消费都会创建一个线程，如果业务层面不做限制，就会导致秒杀超卖
         */
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("redis-listener-pool-%d").build();
        Executor executor = new ThreadPoolExecutor(
                1,
                1,
                5L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                factory);
        container.setTaskExecutor(executor);
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法   （利用反射来创建监听到消息之后的执行方法）
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * 注册订阅者
     *
     * @param latch
     * @return
     */
    @Bean
    ReceiverRedisMessage receiver(CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }

    /**
     * 计数器，用来控制线程
     *
     * @return
     */
    @Bean
    public CountDownLatch latch() {
        //指定了计数的次数 1
        return new CountDownLatch(1);
    }
}
