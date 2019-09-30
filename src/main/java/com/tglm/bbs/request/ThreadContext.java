package com.tglm.bbs.request;

import com.tglm.bbs.exception.ServiceException;

/**
 * @author mlgt
 * @date 2019/9/28
 */

public class ThreadContext {
    Session session;
    private static final ThreadLocal<ThreadContext> threadLocal = new ThreadLocal<>();

    public static Session getSession() throws ServiceException {
        if(threadLocal.get() == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"无法获取ThreadContext");
        }
        if(threadLocal.get().session == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"无法获取Session");
        }
        return threadLocal.get().session;
    }

    public static void setSession(Session newSession){
        threadLocal.set(new ThreadContext(){
            {
                session = newSession;
            }
        });

    }


}
