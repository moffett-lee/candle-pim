package com.rainze.cloud.assembly.controller;

import com.rainze.cloud.assembly.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@RestController
public class ProducerController {

        @Autowired
        private SendService sendService;

        @RequestMapping("/send/{msg}")
        public void send(@PathVariable("msg") String msg){
            sendService.sendMsg(msg);
        }
}
