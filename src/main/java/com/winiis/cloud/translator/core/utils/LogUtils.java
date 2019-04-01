package com.winiis.cloud.translator.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * LogUtils.
 *
 * @author zj.
 *         Created on 2018/8/29 0029.
 */
public class LogUtils {
    private final static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    private final static String PROGRAM_EXECUTE_START_KEY = "PROGRAM_ID";

    public static void setProgramStart() {
        ContextUtils.put(PROGRAM_EXECUTE_START_KEY, System.currentTimeMillis());
    }

    public static Long getProgramStart() {
        return ContextUtils.get(PROGRAM_EXECUTE_START_KEY);
    }

    public static String getIP(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Real-IP");
            if (StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
            ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            } else {
                return request.getRemoteAddr();
            }
        } catch (Exception ex) {
            return "";
        }
    }
}
