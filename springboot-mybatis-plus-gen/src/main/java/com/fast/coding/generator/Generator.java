package com.fast.coding.generator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.fast.coding.common.utils.CompressFileUtil;
import com.fast.coding.common.utils.FileUtil;
import com.fast.coding.generator.base.AbstractFastCodingGenerator;
import com.fast.coding.generator.config.GeneratorConfig;
import com.fast.coding.generator.core.model.GeneratorInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

/**
 * FastCoding代码生成器
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Data
@Slf4j
public class Generator extends AbstractFastCodingGenerator {

    /**
     * 构建一个Generator对象
     * @param generatorInfo 生成信息
     * @return this
     */
    public static Generator builder(GeneratorInfo generatorInfo) {
        Generator generator = new Generator();
        generator.init(generatorInfo);
        return generator;
    }

    @Override
    public String execute() throws Exception {
        // 设置生成临时路径
        String tmpdir = System.getProperty("java.io.tmpdir") + "fastCoding." + IdWorker.getIdStr();
        generatorInfo.setTempPath(tmpdir);
        // 配置MybatisPlus,并执行生成
        AutoGenerator autoGenerator = this.setMybatisPlusConfig(generatorInfo);
        autoGenerator.execute();
        log.info("===> 代码生成完毕! 正在努力压缩文件中...");
        // 生成完成进行压缩处理
        if(FileUtil.validateFileDir(tmpdir)) {
            FileOutputStream fos = new FileOutputStream(new File(tmpdir + "code.zip"));
            CompressFileUtil.toZip(tmpdir,"fastCoding_" + generatorInfo.getModuleName(),fos,true);
        }
        return tmpdir;
    }

    /**
     * mp配置
     * @param generatorInfo 生成信息
     * @return {@link AutoGenerator}
     */
    private AutoGenerator setMybatisPlusConfig(GeneratorInfo generatorInfo) {
        AutoGenerator generator = new AutoGenerator();
        // MybatisPlus 配置
        GeneratorConfig config = new GeneratorConfig().initConfig(generatorInfo);
        generator.setGlobalConfig(config.initGlobalConfig());
        generator.setDataSource(config.initDataSourceConfig());
        generator.setStrategy(config.initStrategyConfig());
        generator.setPackageInfo(config.initPackageConfig());
        generator.setTemplate(config.initTemplateConfig());
        generator.setTemplateEngine(config.initTemplateEngine());
        return generator;
    }
}
