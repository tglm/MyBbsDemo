package com.tglm.bbs.upload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mlgt
 * @date 2019/9/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Upload {

    String value() default "";
    String file() default "default";
    String[] extension() default {};
    long maxfile() default 0;
    long maxfileSize() default 0;


}
