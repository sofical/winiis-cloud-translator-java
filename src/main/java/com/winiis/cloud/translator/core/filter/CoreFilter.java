package com.winiis.cloud.translator.core.filter;

import com.alibaba.fastjson.JSON;
import com.winiis.cloud.translator.core.utils.AgentContextUtils;
import com.winiis.cloud.translator.core.utils.LogUtils;
import com.winiis.cloud.translator.core.utils.MixRequestWrapper;
import com.winiis.cloud.translator.core.utils.MixResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AgentFilter.
 *
 * @author zj.
 *         Created on 2018/11/19 0019.
 */
@WebFilter("/*")
@Component
public class CoreFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(CoreFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        MixRequestWrapper mixRequestWrapper = new MixRequestWrapper(request);
        MixResponseWrapper responseWrapper = new MixResponseWrapper(response);

        //开始记录程序逻辑执行时间
        LogUtils.setProgramStart();

        filterChain.doFilter(mixRequestWrapper, responseWrapper);

        //日志相关
        String ip = LogUtils.getIP(request);
        String requestURI = request.getRequestURI();
        String requestParam = JSON.toJSONString(AgentContextUtils.getParams());
        try {
            responseWrapper.getOutputStream();
        } catch (IOException e) {
            logger.info("响应内容失败：" + e.getMessage());
        }
        String result = new String(responseWrapper.getBytes());

        //计算执行时间
        Long start = LogUtils.getProgramStart();
        Long timeCost = System.currentTimeMillis() - start;

        try {
            response.setStatus(responseWrapper.getStatus());
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        } catch (Exception e) {
            logger.info("响应内容失败：" + e.getMessage());
        }
        if (!"/favicon.ico".equals(requestURI)) {
            logger.info("ip:{} 调用接口,请求地址为:{}, 请求参数为:{},返回值是{},[{}]ms", ip, requestURI, requestParam, result,
                    timeCost);
        }
    }

    @Override
    public void destroy() {

    }
}
