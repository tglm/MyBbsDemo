package com.tglm.bbs.upload;

import com.tglm.bbs.Util.RequestUtil;
import com.tglm.bbs.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.servlet.support.RequestContext;

import java.io.File;
import java.util.List;


/**
 *
 * @author mlgt
 * @date 2019/9/10
 */
@Aspect
public aspect UploadAspect {


    @Before("@annotation(upload)")
    public void fileCheck(Upload upload) throws ServiceException {

        List<File> files = RequestUtil.getRequestContext().


        //TODO： 检查文件数量、文件大小、文件拓展名
        if (upload.maxfile() > 0 && ) {
            throw ServiceException.forCode(ServiceException.TOO_MANY_FILES);
        }





    }
}









