package com.fast.coding.generator.core.model;

import lombok.Data;

/**
 * 代码生成扩展模型
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
public class ExtTemplates {

    /**
     * 存放的文件夹,例如：controller html
     */
    private String path;

    /**
     * 模板地址(不需要.ftl)
     */
    private String templateFilePath;

    /**
     * 输出文件名称后缀(不能为null), 实体名称${outFileName}.fileType
     * 例如用户模块：outFileName为空默认为 user.html ,假设outFileName为_add 输出文件名为 --> user_add.html
     */
    private String outFileName;

    /**
     * 文件类型（扩展名）
     */
    private String fileType;


    public ExtTemplates(String path, String templateFilePath, String outFileName, String fileType) {
        this.path = path;
        this.templateFilePath = templateFilePath;
        this.outFileName = outFileName;
        this.fileType = fileType;
    }
}
