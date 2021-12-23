package com.rainze.cloud.assembly.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Repository;
/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@Repository
public interface MyChannel {


    String myChannel = "myChannel";

    @Input(MyChannel.myChannel)
    SubscribableChannel myInput();
}
