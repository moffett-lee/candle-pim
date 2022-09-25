package com.fast.coding.generator.core.controller;

import com.fast.coding.common.constant.SystemConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图控制器
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Controller
public class ViewController{

    private static final String PREFIX = SystemConst.VIEW_PREFIX + "gen";

    @GetMapping("/gen")
    public String index() {
        return PREFIX + "/gen";
    }

    @GetMapping("/gen/add")
    public String add() {
        return PREFIX + "/gen_add";
    }

}
