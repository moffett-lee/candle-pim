package com.rainze.websocket.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-springboot-websocket
 * @data 2020/5/5 11:17
 */
@Controller
public class MyController {
    @RequestMapping("/test")
    public String test(){
        return "client";
    }

}
