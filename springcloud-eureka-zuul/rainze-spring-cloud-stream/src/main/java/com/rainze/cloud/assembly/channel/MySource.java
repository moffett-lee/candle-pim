package com.rainze.cloud.assembly.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:41
 */
public interface MySource {

    String str = "myOutput";   //管道名称为"myOutput"
    @Output(str)
    MessageChannel myOutput();
}