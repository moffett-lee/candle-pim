package com.amber.insect.cat.demo.stock.catutils;

import com.alidaodao.app.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
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
