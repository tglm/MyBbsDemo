package com.tglm.bbs.refresh;

import com.tglm.bbs.util.RedisUtil;
import com.tglm.bbs.exception.ServiceException;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mlgt
 * @date 2019/9/28
 */
@Aspect
@Component
public class RefreshAspect {
    private final RedisUtil redisUtil;

    @Autowired
    public RefreshAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @After("@annotation(refresh)")
    public void refresh(Refresh refresh) throws ServiceException {
        redisUtil.updateSession();
    }

}
