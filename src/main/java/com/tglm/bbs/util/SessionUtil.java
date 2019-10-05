package com.tglm.bbs.util;


import com.tglm.bbs.entities.Admin;
import com.tglm.bbs.entities.User;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.request.ThreadContext;
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

    public static boolean valid() throws ServiceException {
        Session session = ThreadContext.getSession();
        final int VALIDITY_PERIOD = 5 * 60 * 1000;
        return (System.currentTimeMillis() - session.getLastAccessTime().getTime()) <= VALIDITY_PERIOD;
    }

    /**
     * 自动存储session到redis
     *
     * @param visitor User
     */
    public Session init(Object visitor) {
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
        Session session;
        String role;
        if(visitor.getClass().equals(User.class)){
            role = "user";
            session = new Session(
                    temporaryId.toString(),
                    date,
                    ((User) visitor).getUsername(),
                    role
            );

        }else{
            role = "admin";
            session = new Session(
                    temporaryId.toString(),
                    date,
                    ((Admin)visitor).getAccount(),
                    role
            );

        }

        redisUtil.save(session);
        return session;

    }


}
