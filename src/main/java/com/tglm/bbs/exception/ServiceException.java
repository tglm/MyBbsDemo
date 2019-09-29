package com.tglm.bbs.exception;

import lombok.Getter;

/**
 * @author mlgt
 * @date 2019/9/2
 */
@Getter
public class ServiceException extends Exception {

    public static final int EXCEED_AUTHORITY = 1001;
    public static final int SESSION_EXPIRED = 1002;
    public static final int WRONG_PARAMETER = 1003;
    public static final int USERNAME_HAS_EXISTED = 1004;
    public static final int WRONG_FORMAT = 1005;
    public static final int NO_SUCH_USERNAME = 1006;
    public static final int WRONG_PASSWORD = 1007;
    public static final int PASSWORD_REPEATED = 1008;
    public static final int TOO_MANY_FILES = 1009;
    public static final int NULL_PARAMETER_ERROR = 1010;
    public static final int NULL_RESPONSE_ERROR = 1011;
    public static final int MKDIR_FAILED = 1012;



    private int code;
    private String message;


    private ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServiceException forCode(int code) {
        return new ServiceException(code, null);

    }

    public static ServiceException forCodeAndMessage(int code, String message){
        return new ServiceException(code,message);
    }

}
