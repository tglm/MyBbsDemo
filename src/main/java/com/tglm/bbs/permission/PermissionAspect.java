package com.tglm.bbs.permission;

import com.tglm.bbs.Util.RedisUtil;
import com.tglm.bbs.Util.RequestUtil;
import com.tglm.bbs.Util.SessionUtil;
import com.tglm.bbs.entities.Session;
import com.tglm.bbs.exception.ServiceException;
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

        if(!SessionUtil.valid(session)){throw ServiceException.forCode(ServiceException.SESSION_EXPIRED);}
        if( !permit.role().equals(session.getRole()) ){
            throw ServiceException.forCode(ServiceException.EXCEED_AUTHORITY);
        }


    }


}
