package com.winiis.cloud.translator.core.exception.vo;

import com.winiis.cloud.translator.core.exception.enums.ExceptionEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * ExceptionVo.
 *
 * @author zj.
 *         Created on 2018/11/16 0016.
 */
public class ExceptionVo extends LinkedHashMap<String, Object> implements Serializable{
    protected String CODE = "code";
    protected String MESSAGE = "message";
    protected String SERVER_TIME ="serverTime";
    public ExceptionVo() {
        put(CODE, "-1");
        put(MESSAGE, "未知异常");
        put(SERVER_TIME, new Date());
    }
    public ExceptionVo(ExceptionEnum exceptionEnum) {
        put(CODE, exceptionEnum.getCode());
        put(MESSAGE, exceptionEnum.getMessage());
        put(SERVER_TIME, new Date());
    }
    public ExceptionVo(String code, String message) {
        put(CODE, code);
        put(MESSAGE, message);
        put(SERVER_TIME, new Date());
    }
}
