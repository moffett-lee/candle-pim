package com.amber.insect.sentinel.demo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;
import java.util.List;

import static sun.security.x509.X509CertInfo.KEY;

/**
 * @ClassName ExceptionRatioDegradeDemo
 * @Description 异常比例演
 * @Author Amber.L
 * @Date 2021/12/6 22:43
 * @Version 1.0
 **/
public class ExceptionRatioDegradeDemo {




    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<DegradeRule>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(KEY);
        // set limit exception ratio to 0.1
        rule.setCount(0.1);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
        rule.setTimeWindow(10);
        // rule.setMinRequestAmount(20);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
