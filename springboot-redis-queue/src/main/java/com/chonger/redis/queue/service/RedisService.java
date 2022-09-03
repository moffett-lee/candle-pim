package com.chonger.redis.queue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author carzy
 * @date 2018/07/18
 */
@Service
public class RedisService {
    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;
    private Logger logger = LoggerFactory.getLogger(RedisService.class);
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 压栈
     *
     * @param key    主键
     * @param source 被存储的对象
     */
    public Long pushObject(String key, Object source) {
        logger.debug("push object : {}", source);
        this.redisTemplate.opsForValue().set(key, source);
        return this.redisTemplate.opsForList().leftPush(key, source);
    }

    /**
     * 出栈
     *
     * @param key 索引主键
     * @return Object 返回对象
     */
    public Object popObject(String key) {
        logger.debug("pop object by key: {}", key);
        return this.redisTemplate.opsForList().leftPop(key);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey 旧key值
     * @param newKey 新key值
     * @return 修改成功返回true否则false
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        logger.info("rename key, old key: {} , new key: {}", oldKey, newKey);
        return this.redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key key
     */
    public void deleteKey(String key) {
        logger.info("delete source by key: {}", key);
        this.redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys 多个key值
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        logger.info("delete source by keys: {}", kSet);
        this.redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys keys集合
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = new HashSet<>(keys);
        logger.info("delete source by keys: {}", kSet);
        this.redisTemplate.delete(kSet);
    }
}
