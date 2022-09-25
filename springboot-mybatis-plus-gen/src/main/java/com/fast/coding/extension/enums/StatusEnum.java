package com.fast.coding.extension.enums;

/**
 * API 返回状态枚举
 *
 * @author Bamboo
 * @since 2020-03-18
 */
public enum StatusEnum {

    // 成功
    OK(200,"请求处理成功"),
    // layUI 数据表格默认成功状态码
    SUCCESS(0,"请求处理成功"),
    // 失败
    FAIL(-1,"请求处理失败"),
    // 服务器异常
    INTERNAL_SERVER_ERROR(500,"服务器内部错误");

    /**
     * 状态码
     */
    public Integer code;

    /**
     * 提示信息
     */
    public String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
