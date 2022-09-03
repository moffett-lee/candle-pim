package com.rainze.shiro.service.impl;

import com.rainze.shiro.dao.UserInfoDao;
import com.rainze.shiro.entity.UserInfo;
import com.rainze.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 17:24
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}
