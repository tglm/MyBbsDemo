package com.tglm.bbs.response;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author mlgt
 * @date 2019/9/10
 */

public class SimpleResponseWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler handlerMethodReturnValueHandler;

    public SimpleResponseWrapHandler(HandlerMethodReturnValueHandler handlerMethodReturnValueHandler) {
        this.handlerMethodReturnValueHandler = handlerMethodReturnValueHandler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return handlerMethodReturnValueHandler.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        JSON jsonBody = (JSON) JSON.toJSON(o);
        handlerMethodReturnValueHandler.handleReturnValue(jsonBody,methodParameter,modelAndViewContainer,nativeWebRequest);

    }
}
