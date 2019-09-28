package com.tglm.bbs.refresh;

import com.tglm.bbs.Util.RedisUtil;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.request.ThreadContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mlgt
 * @date 2019/9/28
 */
@Aspect
@Component
public class RefreshAspect {
    final RedisUtil redisUtil;

    @Autowired
    public RefreshAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Before("@annotation(refresh)")
    public void refresh(Refresh refresh) throws ServiceException {
        Session session = ThreadContext.session();
        redisUtil.updateSession(session);
    }

}
