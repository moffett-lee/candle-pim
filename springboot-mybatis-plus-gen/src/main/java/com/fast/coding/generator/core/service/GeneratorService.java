package com.fast.coding.generator.core.service;

import com.fast.coding.extension.pagination.LayPage;

/**
 * 代码生成Service 接口
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public interface GeneratorService {

    /**
     * 获取数据库中的表
     *
     * @param tableName 表名
     * @param tableDescribe 表描述
     * @return 数据库中的表集合(分页)
     */
    LayPage getTables(String tableName, String tableDescribe);


    /**
     * 获取表所有字段
     *
     * @param tableName 表名
     * @return 表字段集合(全部)
     */
    LayPage getFields(String tableName);
}
