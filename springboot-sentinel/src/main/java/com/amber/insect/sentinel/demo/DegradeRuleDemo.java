package com.amber.insect.sentinel.demo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;
import java.util.List;

import static sun.security.x509.CertificateX509Key.KEY;

/**
 * @ClassName DegradeRuleDemo
 * @Description 规则演示
 * @Author Amber.L
 * @Date 2021/12/6 22:34
 * @Version 1.0
 **/
public class DegradeRuleDemo {


    public static void main(String[] args) {
        initDegradeRule();
    }


    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<DegradeRule>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(KEY);
        // set threshold rt, 10 ms
        rule.setCount(10);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }


}
