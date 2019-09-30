package com.tglm.bbs.request;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author mlgt
 * @date 2019/9/29
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    private ThreadLocal<ThreadContext> threadContextThreadLocal = new ThreadLocal<>();


    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        threadContextThreadLocal.remove();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//        String sessionId = (String) request.getAttribute("sessionId");
////        Session session = (Session) redisTemplate.opsForValue().get(sessionId);
////        ThreadContext threadContext = new ThreadContext();
////        threadContext.setSession(session);
//        threadContextThreadLocal.set(threadContext);


    }

}
