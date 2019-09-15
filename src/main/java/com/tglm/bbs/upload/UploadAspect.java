package com.tglm.bbs.upload;

import com.tglm.bbs.Util.RedisUtil;
import com.tglm.bbs.Util.RequestUtil;
import com.tglm.bbs.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author mlgt
 * @date 2019/9/15
 */
@Aspect
@Component
public class UploadAspect {

    private final RedisUtil redisUtil;

    @Autowired
    public UploadAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Before("@annotation(upload)")
    public void fileCheck(Upload upload) throws ServiceException {
        HttpServletRequest request = RequestUtil.getRequest();
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multiRequest.getFiles("avatar");


        //TODO： 检查文件数量、文件大小、文件拓展名、是否为空
        if (upload.maxfile() > 0 && files.size()>upload.maxfile()) {
            throw ServiceException.forCode(ServiceException.TOO_MANY_FILES);
        }


    }
}

