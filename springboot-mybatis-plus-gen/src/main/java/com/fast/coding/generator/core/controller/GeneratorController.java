package com.fast.coding.generator.core.controller;

import com.fast.coding.common.constant.GenConst;
import com.fast.coding.common.utils.FileUtil;
import com.fast.coding.extension.pagination.LayPage;
import com.fast.coding.generator.Generator;
import com.fast.coding.generator.config.DbConfig;
import com.fast.coding.generator.core.model.ExtTemplates;
import com.fast.coding.generator.core.model.GeneratorInfo;
import com.fast.coding.generator.core.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成控制器
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/gen")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private DbConfig dbConfig;

    @RequestMapping("list")
    public LayPage list(String tableName, String tableDescribe) {
        return generatorService.getTables(tableName,tableDescribe);
    }

    @RequestMapping("getFields")
    public LayPage getFields(String tableName) {
        return generatorService.getFields(tableName);
    }

    @RequestMapping("execute")
    public void execute(HttpServletResponse response, @RequestBody GeneratorInfo generatorInfo) throws Exception {
        // 设置数据源
        generatorInfo.setDataSource(dbConfig);
        // 追加模板
        List<ExtTemplates> templatesList = new ArrayList<>();
        if(generatorInfo.getCreatePage()) {
            templatesList.add(new ExtTemplates("html","/templates/ftl/page.html", "", GenConst.DOT_HTML));
            templatesList.add(new ExtTemplates("html","/templates/ftl/page_add.html", "_add", GenConst.DOT_HTML));
            templatesList.add(new ExtTemplates("html","/templates/ftl/page_edit.html","_edit", GenConst.DOT_HTML));
        }
        generatorInfo.setExtendTemplate(templatesList);
        // 构建生成器
        Generator generator = Generator.builder(generatorInfo);
        String path = generator.execute();
        // 下载并删除临时文件
        FileUtil.downloadFile(path + "code.zip","code.zip",true,response);
        FileUtil.deleteFile(path);
    }
}
