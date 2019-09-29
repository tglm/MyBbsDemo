package com.tglm.bbs.request;

import com.tglm.bbs.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mlgt
 * @date 2019/9/29
 */
@Component
public class RequestListener implements ServletRequestListener {

    ThreadLocal<ThreadContext> threadContextThreadLocal = new ThreadLocal<>();


    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        threadContextThreadLocal.remove();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String sessionId = request.getParameter("sessionId");
        ThreadContext threadContext = new ThreadContext();
        //怎么拿到Session呢
        Session session =
        threadContext.setSession(session);
        threadContextThreadLocal.set(threadContext);

    }

}
