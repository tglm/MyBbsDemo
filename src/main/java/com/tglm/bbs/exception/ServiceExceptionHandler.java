package com.tglm.bbs.exception;


import com.tglm.bbs.response.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mlgt
 * @date 2019/9/7
 */
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public SimpleResponse serviceException(ServiceException exception){
        exception.printStackTrace();
        return new SimpleResponse(exception.getCode(),exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleResponse internalException(Exception exception) {
        exception.printStackTrace();
        return new SimpleResponse(500, exception.getMessage());
    }

}
