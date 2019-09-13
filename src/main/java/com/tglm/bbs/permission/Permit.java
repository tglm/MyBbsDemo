package com.tglm.bbs.permission;

/**
 * @author mlgt
 * @date 2019/9/10
 */
public @interface Permit {
    String role() default "user";
    String value() default "";



}
