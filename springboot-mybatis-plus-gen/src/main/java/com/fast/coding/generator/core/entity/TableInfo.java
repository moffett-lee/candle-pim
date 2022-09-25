package com.fast.coding.generator.core.entity;

import lombok.Data;

/**
 * 表信息
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDescribe;

    /**
     * 数据行
     */
    private Long dataRows;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建时间
     */
    private String updateTime;

}
