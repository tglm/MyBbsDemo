package com.tglm.bbs.request;

import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.entities.User;
import com.tglm.bbs.exception.ServiceException;

/**
 * @author mlgt
 * @date 2019/9/28
 */

public class ThreadContext {
    Session session;
    User operator;
    Post post;
    Comment comment;

    private static final ThreadLocal<ThreadContext> threadLocal = new ThreadLocal<>();

    public static Session getSession() throws ServiceException {
        if(threadLocal.get() == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"获取ThreadContext失败");
        }
        if(threadLocal.get().session == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"获取Session失败");
        }
        return threadLocal.get().session;
    }

    static void signIn(Session newSession, User user){
        threadLocal.set(new ThreadContext(){
            {
                operator = user;
                session = newSession;
            }
        });

    }


    public static User getOperator() throws ServiceException {
        if(threadLocal.get() == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"获取ThreadContext失败");
        }
        if(threadLocal.get().operator == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"获取Session失败");
        }
        return threadLocal.get().operator;
    }




}
