package com.fast.coding.common.constant;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.util.Arrays;
import java.util.List;

/**
 * 代码生成相关常量
 *
 * @author Bamboo
 * @since 2020-03-18
 */
public interface GenConst extends StringPool {

    /**
     * 文件后缀
     */
    String DOT_HTML = ".html";
    String DOT_JS = ".js";
    String DOT_SQL = ".sql";

    /**
     * 数据库类型
     */
    String DB_MYSQL= "mysql";

    /**
     * 生成模板  1.0.0只有MyBatis-Plus
     */
    List<String> TEMPLATE = Arrays.asList("MyBatis-Plus");
}
