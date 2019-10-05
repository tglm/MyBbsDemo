package com.tglm.bbs.security.permission;

import com.tglm.bbs.util.RedisUtil;
import com.tglm.bbs.util.RequestUtil;
import com.tglm.bbs.util.SessionUtil;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.security.exception.ServiceException;
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
public class PermissionAspect {
    private final RedisUtil redisUtil;

    @Autowired
    public PermissionAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Before("@annotation(permit)")
    public void permissionCheck(Permit permit) throws ServiceException {
        if("".equals(permit.role())){return;}

        Session session = redisUtil.getSession(RequestUtil.getHeaderInfo().getSessionId());

        if(!SessionUtil.valid()){throw ServiceException.forCode(ServiceException.SESSION_EXPIRED);}
        if( !permit.role().equals(session.getRole()) ){
            throw ServiceException.forCode(ServiceException.EXCEED_AUTHORITY);
        }


    }


}
