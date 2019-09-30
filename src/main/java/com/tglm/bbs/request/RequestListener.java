package com.tglm.bbs.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mlgt
 * @date 2019/9/29
 */
@Component
@WebListener
public class RequestListener implements ServletRequestListener {

    final private RedisTemplate redisTemplate;

    @Autowired
    public RequestListener(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String sessionId = request.getHeader("session-id");
        if(sessionId == null){
            return;
        }
        Session session = (Session) redisTemplate.opsForValue().get(sessionId);
        ThreadContext.setSession(session);

    }

}
