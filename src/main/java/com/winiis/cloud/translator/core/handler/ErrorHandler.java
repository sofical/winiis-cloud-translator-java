package com.winiis.cloud.translator.core.handler;

import com.winiis.cloud.translator.core.exception.vo.ExceptionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Created by Administrator on 2017/11/22.
 */

@ControllerAdvice
public class ErrorHandler {
    private final static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExceptionVo errorResposne(Exception e) {
        ExceptionVo exception = new ExceptionVo("-98", e.getMessage());
        logger.info("程序处理异常：" + e.getMessage(), e);
        return exception;
    }
}
