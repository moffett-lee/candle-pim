package com.fast.coding.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.fast.coding.common.constant.GenConst;
import com.fast.coding.generator.core.model.GeneratorInfo;
import com.fast.coding.generator.engine.FastCodingTemplateEngine;

import java.util.ArrayList;

/**
 * Mybatis-Plus代码生成配置类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class GeneratorConfig {

    /**
     * 生成信息
     */
    private GeneratorInfo generatorInfo;

    public GeneratorConfig initConfig(GeneratorInfo generatorInfo) {
        this.generatorInfo = generatorInfo;
        return this;
    }

    /**
     * 全局配置
     */
    public GlobalConfig initGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        // 是否支持AR模式
        globalConfig.setActiveRecord(true);
        // 作者
        globalConfig.setAuthor(generatorInfo.getAuthor());
        // 生成路径
        globalConfig.setOutputDir(generatorInfo.getTempPath());
        // 文件覆盖
        globalConfig.setFileOverride(true);
        // 主键策略
        globalConfig.setIdType(IdType.ASSIGN_ID);
        // 是否开启二级缓存
        globalConfig.setEnableCache(false);
        // 设置生成的service接口的名字的首字母是否为I
        globalConfig.setServiceName("%sService");
        // ResultMap
        globalConfig.setBaseResultMap(true);
        // 字段集合
        globalConfig.setBaseColumnList(true);
        // 时间类型采用Date
        globalConfig.setDateType(DateType.ONLY_DATE);
        // 是否打开文件夹
        globalConfig.setOpen(false);
        return globalConfig;
    }

    /**
     * 数据源配置
     */
    public DataSourceConfig initDataSourceConfig() {
        // 获取数据源配置
        DbConfig db = generatorInfo.getDataSource();
        String jdbcUrl = db.getUrl();

        // 连接信息
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(db.getDriverClassName());
        dataSourceConfig.setUrl(jdbcUrl);
        dataSourceConfig.setUsername(db.getUserName());
        dataSourceConfig.setPassword(db.getPassword());
        // TODO 其他常用数据库正在开发中，1.0.0只支持MYSQL
        if (jdbcUrl.contains(GenConst.DB_MYSQL)) {
            dataSourceConfig.setDbType(DbType.MYSQL);
        }else {
            dataSourceConfig.setDbType(DbType.OTHER);
        }
        return dataSourceConfig;
    }

    /**
     * 策略配置
     */
    public StrategyConfig initStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // Lombok
        strategyConfig.setEntityLombokModel(true);
        // 是否生成实体时，生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        // 表前缀
        strategyConfig.setTablePrefix(generatorInfo.getTablePrefix());
        // 表名
        strategyConfig.setInclude(generatorInfo.getTableName());
        // 公共字段填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
        strategyConfig.setTableFillList(tableFills);
        return strategyConfig;
    }

    /**
     * 包配置
     */
    public PackageConfig initPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(generatorInfo.getPackageName() + GenConst.DOT + generatorInfo.getModuleName());
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setXml("mapper.xml");
        return packageConfig;
    }

    /**
     * 模板配置
     */
    public TemplateConfig initTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        String template = generatorInfo.getTemplate();
        String prefix = "/templates/ftl/";
        if(GenConst.TEMPLATE.contains(template)) {
            templateConfig.setController(prefix + template + "/controller.java");
            templateConfig.setEntity(prefix + template + "/entity.java");
            templateConfig.setService(prefix + template + "/service.java");
            templateConfig.setServiceImpl(prefix + template + "/serviceImpl.java");
            templateConfig.setMapper(prefix + template + "/mapper.java");
            templateConfig.setXml(prefix + template + "/mapper.xml");
        }
        return templateConfig;
    }

    /**
     * 模板引擎
     */
    public AbstractTemplateEngine initTemplateEngine() {
        FastCodingTemplateEngine templateEngine = new FastCodingTemplateEngine();
        templateEngine.setGeneratorInfo(generatorInfo);
        return templateEngine;
    }

}
