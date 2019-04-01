package com.winiis.cloud.translator.core.utils;

import java.util.HashMap;

/**
 * ContextUtils.
 *
 * @author zj.
 *         Created on 2018/10/29 0029.
 */
final class ContextUtils {
    /**
     * 线程存储
     */
    private static ThreadLocal<HashMap> local = new ThreadLocal();

    /**
     * 取出所有线程存储.
     * @return HashMap.
     */
    public static HashMap getLocal() {
        HashMap hashMap = local.get();
        if(hashMap == null) {
            hashMap = new HashMap();
            local.set(hashMap);
        }

        return hashMap;
    }

    /**
     * 获取存储对象.
     * @param key key.
     * @param <T> T.
     * @return T.
     */
    public static <T> T get(String key) {
        if (!getLocal().containsKey(key)) {
            return null;
        }
        return (T) getLocal().get(key);
    }

    /**
     * 存储对象
     * @param key key.
     * @param value Object.
     */
    public static void put(String key, Object value) {
        getLocal().put(key, value);
    }
}
