package com.tglm.bbs.response;

import lombok.Data;

/**
 *
 * @author tglm
 * @date 2019/8/25
 */
@Data
public class SimpleResponse{
    private int code;
    private Object data;

    public SimpleResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    SimpleResponse(Object data) {
        this.data = data;
        this.code = 0;
    }
}
