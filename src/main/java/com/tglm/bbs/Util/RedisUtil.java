package com.tglm.bbs.Util;

import com.tglm.bbs.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

    public void updateRedis(Session session) {

        redisTemplate.opsForValue().getAndSet(session.getSessionId(), session);

    }

    public Session getSession(String sessionId) {
        if (sessionId.isEmpty()) {
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(sessionId);

    }


}

