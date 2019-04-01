package com.winiis.cloud.translator.core.utils;

import java.util.Map;

/**
 * AgentContextUtils.
 *
 * @author zj.
 *         Created on 2018/11/19 0019.
 */
public class AgentContextUtils {
    /**
     * 上下文Key.
     */
    final private static String CONTEXT_KEY_PARAMS = "R_P";

    /**
     * 设置请求参数.
     * @param params 参数.
     */
    public static void setParams(Map params) {
        ContextUtils.put(CONTEXT_KEY_PARAMS, params);
    }

    /**
     * 获取请求参数.
     * @return 请求参数
     */
    public static Map getParams() {
        return ContextUtils.get(CONTEXT_KEY_PARAMS);
    }

    /**
     * 获取参数值.
     * @param paramName 参数名称.
     * @param <T>
     * @return value.
     */
    public static <T> T getParams(String paramName) {
        Map<String, Object> request = getParams();
        if (null == request) {
            return null;
        }
        if (request.containsKey(paramName)) {
            return (T) request.get(paramName);
        } else {
            return null;
        }
    }
}
