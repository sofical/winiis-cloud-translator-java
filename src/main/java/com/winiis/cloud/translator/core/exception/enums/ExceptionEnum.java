package com.winiis.cloud.translator.core.exception.enums;

/**
 * ExceptionEnum.
 *
 * @author zj.
 *         Created on 2018/11/16 0016.
 */
public enum ExceptionEnum {
    /**
     * 未知错误.
     */
    UNKNOWN("-99", "未知错误"),
    /**
     * 成功.
     */
    SUCCESSFUL("0", "操作成功");
    /**
     * 错误码,
     */
    private String code;
    /**
     * 错误描述.
     */
    private String message;

    /**
     * construct.
     * @param code code.
     * @param message message.
     */
    ExceptionEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误码值.
     * @return String.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 获取错误描述.
     * @return String.
     */
    public String getMessage() {
        return this.message;
    }
}
