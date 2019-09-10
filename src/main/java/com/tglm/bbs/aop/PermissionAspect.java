package com.tglm.bbs.aop;

import com.tglm.bbs.Util.RedisUtil;
import com.tglm.bbs.entities.Session;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mlgt
 * @date 2019/9/10
 */
@Aspect
@Component
public class PermitAspect {
    private final RedisUtil redisUtil;
    private final RequestUtil requestUtil;

    @Autowired
    public PermitAspect(RedisUtil redisUtil, RequestUtil requestUtil) {
        this.redisUtil = redisUtil;
        this.requestUtil = requestUtil;
    }

    @Before("@annotation(permit)")
    public void permissionCheck(Permit permit) throws ServiceException {

        Session session = redisUtil.getSession(requestUtil.getHeaderInfo().getSessionId());

        if(!SessionUtil.valid(session)){throw ServiceException.forCode(ServiceException.SESSION_EXPIRED);}
        if( !permit.role().equals(session.getRole()) ){
            throw ServiceException.forCode(ServiceException.EXCEED_AUTHORITY);
        }


    }


}
