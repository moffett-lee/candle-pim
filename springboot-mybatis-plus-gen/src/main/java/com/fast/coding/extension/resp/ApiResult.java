package com.fast.coding.extension.resp;

import com.fast.coding.extension.enums.StatusEnum;

import java.io.Serializable;

/**
 * API 返回结果封装
 *
 * @author Bamboo
 * @since 2020-03-18
 */
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    private ApiResult(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     * @return {ApiResult}
     */
    public static ApiResult success() {
        return new ApiResult(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMessage(),"");
    }

    /**
     * 成功
     * @return {ApiResult}
     */
    public static ApiResult success(String msg) {
        return new ApiResult(true, StatusEnum.OK.getCode(), msg, "");
    }

    /**
     * 成功
     * @return {ApiResult}
     */
    public static ApiResult success(Object data) {
        return new ApiResult(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMessage(), data);
    }

    /**
     * 成功
     * @return {ApiResult}
     */
    public static ApiResult success(String msg, Object data) {
        return new ApiResult(true, StatusEnum.OK.getCode(), msg, data);
    }

    /**
     * 错误
     * @return {ApiResult}
     */
    public static ApiResult error(String msg) {
        return new ApiResult(false, StatusEnum.INTERNAL_SERVER_ERROR.getCode(), msg, "");
    }

    /**
     * 错误
     * @return {ApiResult}
     */
    public static ApiResult error(Integer code, String msg) {
        return new ApiResult(false, code, msg, "");
    }

    /**
     * 错误
     * @return {ApiResult}
     */
    public static ApiResult error(Integer code, String msg, Object data) {
        return new ApiResult(false, code, msg, data);
    }

    /**
     * 自定义提示
     * @return {ApiResult}
     */
    public static ApiResult msg(Boolean success, Integer code, String msg, Object data) {
        return new ApiResult(success, code, msg, data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
