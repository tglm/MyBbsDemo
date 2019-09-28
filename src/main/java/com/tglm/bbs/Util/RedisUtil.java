package com.tglm.bbs.Util;

import com.tglm.bbs.request.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author tglm
 * @date 2019/8/26
 */

@Component
public class RedisUtil {

    private final RedisTemplate redisTemplate;


    @Autowired
    public RedisUtil(RedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

        void save(Session session) {

        redisTemplate.opsForValue().set(session.getSessionId(), session);
    }

    public void updateSession(Session session) {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        session.setLastAccessTime(date);
        redisTemplate.opsForValue().getAndSet(session.getSessionId(), session);

    }

    public Session getSession(String sessionId) {
        if (sessionId.isEmpty()) {
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(sessionId);

    }


}

