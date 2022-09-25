package com.fast.coding.generator.core.entity;

import lombok.Data;

/**
 * 字段信息
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
public class FieldInfo {

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段描述
     */
    private String fieldComment;

    /**
     * 字段SQL类型
     */
    private String dataType;

    /**
     * 是否为页面快速查询的字段
     */
    private boolean queryField;
}
