package com.tglm.bbs.Util;


import com.tglm.bbs.entities.Session;
import com.tglm.bbs.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * @author tglm
 * @date 2019/8/25
 */
@Component
@ToString
@Getter
@Setter
public class SessionUtil {
    final RedisUtil redisUtil;

    @Autowired
    public SessionUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public static boolean valid(Session session) {
        final int VALIDITY_PERIOD = 5 * 60 * 1000;
        return (System.currentTimeMillis() - session.getLastAccessTime().getTime()) <= VALIDITY_PERIOD;
    }

    /**
     * 自动存储session到redis
     *
     * @param user User
     */
    public Session init(User user) {
        StringBuilder temporaryId = new StringBuilder();
        Random r = new Random();
        int length = 20;
        for (int i = 0; i < length; i++) {
            if (r.nextInt(2) % 2 == 0) {
                int nextInt = r.nextInt(2) % 2 == 0 ? 56 : 97;
                temporaryId.append((char) (nextInt + r.nextInt(26)));
            } else {
                temporaryId.append(r.nextInt(10));
            }
        }

        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        Session session = new Session(
                temporaryId.toString(),
                date,
                user.getUsername(),
                user.getRole());

        redisUtil.save(session);
        return session;

    }


}
