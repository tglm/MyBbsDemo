package com.tglm.bbs.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    private ThreadLocal<ThreadContext> threadContextThreadLocal = new ThreadLocal<>();

    final private RedisTemplate redisTemplate;

    @Autowired
    public RequestListener(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        threadContextThreadLocal.remove();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String sessionId = (String) request.getAttribute("sessionId");
        Session session = (Session) redisTemplate.opsForValue().get(sessionId);
        ThreadContext threadContext = new ThreadContext();
        threadContext.setSession(session);
        threadContextThreadLocal.set(threadContext);

    }

}
