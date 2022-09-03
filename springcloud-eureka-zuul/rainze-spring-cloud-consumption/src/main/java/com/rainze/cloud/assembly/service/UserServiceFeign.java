package com.rainze.cloud.assembly.service;

import com.rainze.cloud.assembly.service.hystrix.UserServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 这里采用的是feign处理负载均衡的。
 *
 *
 * @FeignClient 注解的value的意思是：用于获取哪个服务的
 *
 */
@FeignClient(value = "rainze-user",fallback = UserServiceHystrix.class)
public interface UserServiceFeign {

    /**
     * @RequestMapping 用于指定调用sea-service-user服务下的 /person/1 的rest服务
     *                        这里我就写死了/person/1，实际上应该有参数传递的，使用的方法与spring的@RequestParam一样
     * @return
     */
    @RequestMapping(value = "/person/1",method = RequestMethod.GET)
    public String getUser();
}
