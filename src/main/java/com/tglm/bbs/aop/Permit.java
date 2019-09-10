package com.tglm.bbs.aop;

/**
 * @author mlgt
 * @date 2019/9/10
 */
public @interface Permit {
    String role() default "user";
    String value() default "";



}
