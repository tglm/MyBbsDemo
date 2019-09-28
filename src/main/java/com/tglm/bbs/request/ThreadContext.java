package com.tglm.bbs.request;

import com.tglm.bbs.exception.ServiceException;
import lombok.Data;

/**
 * @author mlgt
 * @date 2019/9/28
 */
@Data
public class ThreadContext {
    Session session;
    static final ThreadLocal<ThreadContext> threadLocal = new ThreadLocal<>();

    public static Session session() throws ServiceException {
        if(threadLocal.get().session == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"无法获取Session");
        }
        return threadLocal.get().session;
    }

}
