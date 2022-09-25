package com.fast.coding.common.constant;

/**
 * 代码生成相关常量
 *
 * @author Bamboo
 * @since 2020-03-20
 */
public interface SystemConst {


    /**
     * 视图相关
     */
    String VIEW_PREFIX = "view/";

    /**
     * 最大长度
     * 由于js的number只能表示15个数字，超出长度会导致精度问题
     * 超出这个长度会转换字符串
     * @see com.fast.coding.config.web.WebConfigurer
     */
    Integer JS_NUMBER_MAX_LENGTH = 12;

    /**
     * JSON输出的时间格式
     */
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
