package com.rainze.cloud.assembly.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringContextUtil implements ApplicationContextAware {
 
 
    private static ApplicationContext context = null;
 
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }
 
 
    // 传入线程中
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }
 
 
    // 国际化使用
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }
 
 
    /// 获取当前环境
    public static String getActiveProfile() {
        if (null != context.getEnvironment()
                && null!= context.getEnvironment().getActiveProfiles()
                &&context.getEnvironment().getActiveProfiles().length >0) {
            return context.getEnvironment().getActiveProfiles()[0];
        }
        return null;
    }
}