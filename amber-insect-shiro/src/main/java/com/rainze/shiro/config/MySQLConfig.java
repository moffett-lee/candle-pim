package com.rainze.shiro.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 17:17
 */
public class MySQLConfig extends MySQL5InnoDBDialect {


    //这个文件关联的是配置文件中最后一个配置，是让 Hibernate 默认创建 InnoDB 引擎并默认使用 utf-8 编码
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }


}
