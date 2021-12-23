package com.rainze.cloud.assembly.controller;

import com.rainze.cloud.assembly.channel.MyChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@EnableBinding(Source.class)
public class KafkaSender {


    @Autowired
    private MyChannel myChannel;

    public void sendMessage(String message) {
        try {
            myChannel.myInput().send(MessageBuilder.withPayload(message).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
