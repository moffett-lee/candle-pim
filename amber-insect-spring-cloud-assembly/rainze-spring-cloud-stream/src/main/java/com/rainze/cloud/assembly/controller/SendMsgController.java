package com.rainze.cloud.assembly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@RestController
public class SendMsgController {
    @Autowired
    private KafkaSender sendS;


    @GetMapping("/send")
    public  String send(String msg){
        System.out.println("发送消息:"+msg);
        sendS.sendMessage(msg);
        return  "success";
    }
}
