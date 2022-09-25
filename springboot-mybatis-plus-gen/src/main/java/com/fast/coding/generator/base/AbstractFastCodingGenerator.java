package com.fast.coding.generator.base;

import com.fast.coding.generator.core.model.GeneratorInfo;

/**
 * FastCoding代码生成器抽象类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public abstract class AbstractFastCodingGenerator {

    /**
     * 生成信息
     */
    protected GeneratorInfo generatorInfo;

    /**
     * 初始化配置
     */
    public void init(GeneratorInfo generatorInfo) {
        this.generatorInfo = generatorInfo;
    }

    /**
     * 执行生成
     * @return 临时路径
     * @throws Exception
     */
    public abstract String execute() throws Exception;

}
