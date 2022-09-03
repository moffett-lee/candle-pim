package com.chonger.redis.queue;

import com.chonger.redis.queue.annotation.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootRedisQueueApplicationTests {

    @Test
    void contextLoads() {



    }

    /**
     * 测试锁
     */
    @RedisLock(lockType = "STUDENT_LOCK", lockField = "123")
    public void testAnonLock() {
        System.out.println("获取到锁，当前线程名称：" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
