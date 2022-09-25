package com.fast.coding.generator.base;

import com.fast.coding.common.constant.GenConst;
import com.fast.coding.extension.pagination.Pagination;
import com.fast.coding.generator.config.DbConfig;
import com.fast.coding.generator.core.entity.FieldInfo;
import com.fast.coding.generator.core.entity.TableInfo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库操作规范抽象类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Component
public abstract class AbstractDataBaseUtil {

    /**
     * 建立链接
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    /**
     * 获取数据库中的表
     * @param page 分页参数
     * @param schemaName 数据库名
     * @param tableName 表名 - 模糊查询
     * @param tableDescribe 表描述 -模糊查询
     * @return
     */
    public abstract Pagination<TableInfo> getTables(Pagination page, String schemaName, String tableName, String tableDescribe);

    /**
     * 获取表字段列表
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return
     */
    public abstract List<FieldInfo> getFields(String schemaName, String tableName);

    /**
     * 获取数据库名称
     *
     * @param dataSource 数据源配置
     * @return 数据库名称
     */
    public String getDbName(DbConfig dataSource) {
        String jdbcUrl = dataSource.getUrl();
        int start = jdbcUrl.lastIndexOf(GenConst.SLASH) + 1;
        int end = jdbcUrl.indexOf(GenConst.QUESTION_MARK);
        return jdbcUrl.substring(start, end);
    }

}
