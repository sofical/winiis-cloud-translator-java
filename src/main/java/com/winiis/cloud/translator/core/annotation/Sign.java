package com.winiis.cloud.translator.core.annotation;

import java.lang.annotation.*;

/**
 * Sign.
 *
 * @author zj.
 *         Created on 2018/8/26 0026.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {
}
