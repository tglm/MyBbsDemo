package com.tglm.bbs.upload;

/**
 * @author mlgt
 * @date 2019/9/10
 */
public @interface Upload {

    String value() default "";
    String file() default "default";
    String[] extension() default {};
    long maxfile() default 0;
    long maxfileSize() default 0;


}
