package com.tglm.bbs.upload;

import com.tglm.bbs.Util.RedisUtil;
import com.tglm.bbs.Util.RequestUtil;
import com.tglm.bbs.entities.Session;
import com.tglm.bbs.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import java.io.File;
import java.util.List;


/**
 *
 * @author mlgt
 * @date 2019/9/10
 */
@Aspect
@Component
public aspect UploadAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Before("@annotation(upload)")
    public void fileCheck(Upload upload) throws ServiceException {

        Session session = redisUtil.getSession(RequestUtil.getHeaderInfo().getSessionId());
        String username = session.getUsername();

        File file = RequestUtil.getRequestContext().


        //TODO： 检查文件数量、文件大小、文件拓展名、是否为空
        if (upload.maxfile() > 0 && ) {
            throw ServiceException.forCode(ServiceException.TOO_MANY_FILES);
        }





    }
}









