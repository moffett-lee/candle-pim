package com.fast.coding.generator.core.model;

import com.fast.coding.generator.config.DbConfig;
import com.fast.coding.generator.core.entity.FieldInfo;
import lombok.Data;

import java.util.List;

/**
 * 代码生成相关信息
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
public class GeneratorInfo {

    /**
     * 数据源
     */
    private DbConfig dataSource;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 作者
     */
    private String author;

    /**
     * 生成模板
     */
    private String template;

    /**
     * 生成路径
     */
    private String tempPath;

    /**
     * 扩展模板
     */
    private List<ExtTemplates> extendTemplate;

    /**
     * 是否生成页面
     */
    private Boolean createPage;

    /**
     * 是否生成SQL
     */
    private Boolean createSql;

    /**
     * 是否使用Swagger
     */
    private Boolean createSwagger;

    /**
     * 字段信息
     */
    private List<FieldInfo> fields;

}
