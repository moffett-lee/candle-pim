package com.rainze.cloud.assembly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:37
 */
@EnableBinding(Source.class)    //这个注解给我们绑定消息通道的，Source是Stream给我们提供的，可以点进去看源码，可以看到output和input,这和配置文件中的output，input对应的。
public class SendService {

        @Autowired
        private Source source;

        public void sendMsg(String msg){
            source.output().send(MessageBuilder.withPayload(msg).build());
        }

}
