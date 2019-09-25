package com.tglm.bbs.upload;

import com.tglm.bbs.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/9/15
 */
@Aspect
@Component
public class UploadAspect {



    @Before("@annotation(upload)")
    public void fileCheck(Upload upload) throws ServiceException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        MultipartHttpServletRequest multipartHttpServletRequest = resolver.resolveMultipart(request);
        List<MultipartFile> files = multipartHttpServletRequest.getFiles("avatar");


        if (upload.maxfile() > 0 && files.size() > upload.maxfile()) {
            throw ServiceException.forCode(ServiceException.TOO_MANY_FILES);
        }
        for (MultipartFile file:files) {
            if (upload.maxfileSize() > 0 && file.getSize() > upload.maxfileSize()){
                throw ServiceException.forCodeAndMessage(ServiceException.TOO_MANY_FILES,"文件数太多");
            }
            if(upload.extension().length > 0 && extensionCheck(upload.extension(),file)){
                throw ServiceException.forCodeAndMessage(ServiceException.WRONG_FORMAT,"文件格式错误");
            }
            if(file.getSize() == 0){
                throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"文件为空");
            }


        }

    }
    private boolean extensionCheck(String[] validExtension, MultipartFile file){
        return Arrays.asList(validExtension).contains(file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).length()-3));

    }



}
