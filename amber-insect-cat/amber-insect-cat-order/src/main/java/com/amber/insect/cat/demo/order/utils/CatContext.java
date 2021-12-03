package com.amber.insect.cat.demo.order.utils;

import java.util.HashMap;
import java.util.Map;

import com.alidaodao.app.Cat;

/**
 * @Author amber.liyuze
 * @Description cat上下文
 * @Date 2021/12/2 23:44
 * @Param  * @param null
 * @Return
 */
public class CatContext implements Cat.Context {
    /**
     * 存储链路监控相关信息
     */
    private Map<String, String> properties = new HashMap<>();


    @Override
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
