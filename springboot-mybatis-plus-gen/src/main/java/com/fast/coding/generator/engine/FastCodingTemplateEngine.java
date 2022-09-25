package com.fast.coding.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fast.coding.common.utils.FileUtil;
import com.fast.coding.generator.core.entity.FieldInfo;
import com.fast.coding.generator.core.model.ExtTemplates;
import com.fast.coding.generator.core.model.GeneratorInfo;
import com.fast.coding.generator.toolkit.TemplateUtil;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 重写 FreemarkerTemplateEngine
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class FastCodingTemplateEngine extends FreemarkerTemplateEngine {

    /**
     * 生成信息
     */
    private GeneratorInfo generatorInfo;


    @Override
    public AbstractTemplateEngine batchOutput() {
        try {
            List<TableInfo> tableInfoList = this.getConfigBuilder().getTableInfoList();
            Iterator var2 = tableInfoList.iterator();

            while(var2.hasNext()) {
                TableInfo tableInfo = (TableInfo)var2.next();
                Map<String, Object> objectMap = this.getObjectMap(tableInfo);
                Map<String, String> pathInfo = this.getConfigBuilder().getPathInfo();
                TemplateConfig template = this.getConfigBuilder().getTemplate();
                InjectionConfig injectionConfig = this.getConfigBuilder().getInjectionConfig();
                if (null != injectionConfig) {
                    injectionConfig.initMap();
                    objectMap.put("cfg", injectionConfig.getMap());
                    List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
                    if (CollectionUtils.isNotEmpty(focList)) {
                        Iterator var9 = focList.iterator();

                        while(var9.hasNext()) {
                            FileOutConfig foc = (FileOutConfig)var9.next();
                            if (this.isCreate(FileType.OTHER, foc.outputFile(tableInfo))) {
                                this.writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                            }
                        }
                    }
                }

                String entityName = tableInfo.getEntityName();
                String controllerFile;
                if (null != entityName && null != pathInfo.get("entity_path")) {
                    controllerFile = String.format((String)pathInfo.get("entity_path") + File.separator + "%s" + this.suffixJavaOrKt(), entityName);
                    if (this.isCreate(FileType.ENTITY, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getEntity(this.getConfigBuilder().getGlobalConfig().isKotlin())), controllerFile);
                    }
                }

                if (null != tableInfo.getMapperName() && null != pathInfo.get("mapper_path")) {
                    controllerFile = String.format((String)pathInfo.get("mapper_path") + File.separator + tableInfo.getMapperName() + this.suffixJavaOrKt(), entityName);
                    if (this.isCreate(FileType.MAPPER, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getMapper()), controllerFile);
                    }
                }

                if (null != tableInfo.getXmlName() && null != pathInfo.get("xml_path")) {
                    controllerFile = String.format((String)pathInfo.get("xml_path") + File.separator + tableInfo.getXmlName() + ".xml", entityName);
                    if (this.isCreate(FileType.XML, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getXml()), controllerFile);
                    }
                }

                if (null != tableInfo.getServiceName() && null != pathInfo.get("service_path")) {
                    controllerFile = String.format((String)pathInfo.get("service_path") + File.separator + tableInfo.getServiceName() + this.suffixJavaOrKt(), entityName);
                    if (this.isCreate(FileType.SERVICE, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getService()), controllerFile);
                    }
                }

                if (null != tableInfo.getServiceImplName() && null != pathInfo.get("service_impl_path")) {
                    controllerFile = String.format((String)pathInfo.get("service_impl_path") + File.separator + tableInfo.getServiceImplName() + this.suffixJavaOrKt(), entityName);
                    if (this.isCreate(FileType.SERVICE_IMPL, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getServiceImpl()), controllerFile);
                    }
                }

                if (null != tableInfo.getControllerName() && null != pathInfo.get("controller_path")) {
                    controllerFile = String.format((String)pathInfo.get("controller_path") + File.separator + tableInfo.getControllerName() + this.suffixJavaOrKt(), entityName);
                    if (this.isCreate(FileType.CONTROLLER, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(template.getController()), controllerFile);
                    }
                }

                // 追加模板
                List<ExtTemplates> extendTemplate = generatorInfo.getExtendTemplate();
                for (ExtTemplates extTemplates : extendTemplate) {
                    String out = TemplateUtil.lowerFirst(tableInfo.getEntityName()) + extTemplates.getOutFileName();
                    controllerFile = generatorInfo.getTempPath() + File.separator + extTemplates.getPath() + File.separator + TemplateUtil.lowerFirst(tableInfo.getEntityName()) + File.separator + out + extTemplates.getFileType();
                    if (this.isCreate(FileType.OTHER, controllerFile)) {
                        this.writer(objectMap, this.templateFilePath(extTemplates.getTemplateFilePath()), controllerFile);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！", e);
            FileUtil.deleteFile(generatorInfo.getTempPath());
        }

        return this;
    }

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = super.getObjectMap(tableInfo);
        // 所有字段信息
        List<FieldInfo> fieldInfos = TemplateUtil.underlineToCamel(generatorInfo.getFields());
        objectMap.put("allFieldInfo",fieldInfos);
        // 首字母小写的实体类名称
        String lowerEntityName = TemplateUtil.lowerFirst(tableInfo.getEntityName());
        objectMap.put("lowerEntityName",lowerEntityName);
        // 首字母小写的service
        String lowerServiceName = TemplateUtil.lowerFirst(tableInfo.getServiceName());
        objectMap.put("lowerServiceName",lowerServiceName);
        // 首字母小写的mapper
        String lowerMapperName = TemplateUtil.lowerFirst(tableInfo.getMapperName());
        objectMap.put("lowerMapperName",lowerMapperName);
        // 主键名称、类型
        String keyPropertyName = "";
        String keyPropertyType = "";
        for (TableField field : tableInfo.getFields()) {
            if (field.isKeyFlag()) {
                keyPropertyName = field.getPropertyName();
                keyPropertyType = field.getPropertyType();
            }
        }
        objectMap.put("keyPropertyName",keyPropertyName);
        objectMap.put("keyPropertyType",keyPropertyType);
        return objectMap;
    }

    public void setGeneratorInfo(GeneratorInfo generatorInfo) {
        this.generatorInfo = generatorInfo;
    }
}
