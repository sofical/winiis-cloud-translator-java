package com.winiis.cloud.translator.core.annotation.impl;

import com.winiis.cloud.translator.core.exception.AgentException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * GrantAspect.
 *
 * @author zj.
 *         Created on 2018/8/26 0026.
 */
@Aspect
@Component
public class SignAspect implements Ordered {
    private final String checkContent = "63e3ff61-a107-4463-b691-b9ced8256101";

    @Around("@within(com.winiis.cloud.translator.core.annotation.Sign) || " +
            "@annotation(com.winiis.cloud.translator.core.annotation.Sign)")
    public Object grantCheck(ProceedingJoinPoint pjp) throws Throwable {
        if (!this.check()) {
            throw new AgentException("签名错误");
        }
        return pjp.proceed();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 检查签名
     * @return
     */
    private boolean check() {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            //todo：接口签权
            return true;
        } catch (Exception e) {
            throw new AgentException("请求解码处理失败!");
        }
    }
}
