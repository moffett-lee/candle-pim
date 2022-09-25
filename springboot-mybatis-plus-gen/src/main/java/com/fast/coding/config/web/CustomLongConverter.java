package com.fast.coding.config.web;

import com.fast.coding.common.constant.SystemConst;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 自定义的序列化类
 *
 * @author Bamboo
 * @since 2020-03-20
 */
public class CustomLongConverter extends StdSerializer<Long> {


    public CustomLongConverter() {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // 超过12个数字用String格式返回，由于js的number只能表示15个数字
        if (value.toString().length() > SystemConst.JS_NUMBER_MAX_LENGTH) {
            jsonGenerator.writeString(value.toString());
        } else {
            jsonGenerator.writeNumber(value);
        }
    }
}
