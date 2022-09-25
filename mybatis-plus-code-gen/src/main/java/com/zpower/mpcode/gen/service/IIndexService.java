package com.zpower.mpcode.gen.service;

import com.fengwenyi.api.result.ResponseTemplate;
import com.zpower.mpcode.gen.vo.CodeGeneratorRequestVo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-12
 */
public interface IIndexService {

    /**
     * 生成代码
     * @param requestVo
     * @return
     */
    ResponseTemplate<Void> codeGenerator(CodeGeneratorRequestVo requestVo);

    /**
     * 升级检查
     * */
    String upgrade(String version);

}
