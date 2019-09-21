package com.tglm.bbs.Util;

import com.tglm.bbs.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author mlgt
 * @date 2019/9/19
 */
public class FileUtil {


    private static String getExtension(String filename){
        return filename.substring(filename.lastIndexOf('.')+1);


    }


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
        response.addHeader(HttpHeaders.CONTENT_TYPE,toContentType(getExtension(filePath)));

    }

    private static String toContentType(String extension) {
        String res ;

        switch (extension.toLowerCase()) {
            case "jpg":
                res = "application/x-jpg";
                break;
            case "jpeg":
                res = "image/jpeg";
                break;
            case "bmp":
                res = "application/x-bmp";
                break;
            case "png":
                res = "image/png";
                break;
            case "gif":
                res = "image/gif";
                break;
            default:
                res = "application/octet-stream";
        }
        return res;
    }

}
