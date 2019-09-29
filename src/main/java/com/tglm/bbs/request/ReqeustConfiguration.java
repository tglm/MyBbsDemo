package com.tglm.bbs.request;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author mlgt
 * @date 2019/9/29
 */
public class ReqeustConfiguration {

    @Bean
    public ServletListenerRegistrationBean<?> registerCustomListener() {
        return new ServletListenerRegistrationBean<>(new RequestListener());
    }

}
