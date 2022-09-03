package com.rainze.shiro.dao;

import com.rainze.shiro.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 17:22
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    /** 通过username查找用户信息*/
    public UserInfo findByUsername(String username);
}
