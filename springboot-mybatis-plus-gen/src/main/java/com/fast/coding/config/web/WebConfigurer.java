package com.fast.coding.config.web;

import com.fast.coding.common.constant.SystemConst;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 全局注册自定义的序列化配置类
 *
 * @author Bamboo
 * @since 2020-03-20
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // 自定义Long类型转换
        builder.serializerByType(Long.class,new CustomLongConverter());
        builder.serializerByType(Long.TYPE,new CustomLongConverter());
        // 时间类型格式转换,默认为long
        builder.indentOutput(true).dateFormat(new SimpleDateFormat(SystemConst.DATE_FORMAT));
        converters.add(0,new MappingJackson2HttpMessageConverter(builder.build()));
    }
}
