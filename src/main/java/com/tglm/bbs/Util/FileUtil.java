package com.tglm.bbs.Util;

import com.tglm.bbs.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author mlgt
 * @date 2019/9/19
 */
public class FileUtil {

    public static void responseWithFile(String filePath) throws IOException, ServiceException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(response == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_RESPONSE_ERROR,"NULL_RESPONSE_ERROR");
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        byte[] buffer = new byte[1024*128];
        FileInputStream fileInputStream = new FileInputStream(filePath);
        while (fileInputStream.read(buffer) > 0){
            servletOutputStream.write(buffer);
        }
        fileInputStream.close();
        response.addHeader(HttpHeaders.CONTENT_TYPE,);

    }

    private String getContentType(String fileName){
        int index = fileName.lastIndexOf('.') + 1;
        String extension = fileName.substring(index);
        switch (extension.toLowerCase()){
            case "jpg":


        }

    }
}
