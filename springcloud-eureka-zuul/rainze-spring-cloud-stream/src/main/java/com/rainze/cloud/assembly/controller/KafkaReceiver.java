package com.rainze.cloud.assembly.controller;

import com.rainze.cloud.assembly.channel.MyChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@EnableBinding(MyChannel.class)
public class KafkaReceiver {


    @StreamListener(MyChannel.myChannel)
    private void receive(String vote) {
        System.out.println("接收消息1 : " + vote);
    }

    @StreamListener(MyChannel.myChannel)
    private void receiveMsg(String vote) {
        System.out.println("接收消息2 : " + vote);
    }


}
