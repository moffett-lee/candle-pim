package com.rainze.shiro.service;

import com.rainze.shiro.entity.UserInfo;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 17:24
 */
public interface UserInfoService {
    /** 通过username查找用户信息*/
    public UserInfo findByUsername(String username);
}
