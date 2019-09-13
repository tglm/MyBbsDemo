package com.tglm.bbs.Util;

import com.tglm.bbs.dto.HeaderInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/8/30
 */
public class RequestUtil {

    public static HeaderInfo getHeaderInfo() {
        HttpServletRequest request = (
                (ServletRequestAttributes)
                        Objects.requireNonNull(
                                RequestContextHolder.getRequestAttributes())
        ).getRequest();
        String sessionId = request.getHeader("session-id");
        String ip = request.getRemoteAddr();
        if (ip == null || "unknown".equals(ip)) {
            ip = "";
        }
        if (sessionId == null || "unknown".equals(sessionId)) {
            sessionId = "";
        }
        return new HeaderInfo(ip, sessionId);
    }


    public static RequestContext getRequestContext(){

        HttpServletRequest request = (
                (ServletRequestAttributes)
                        Objects.requireNonNull(
                                RequestContextHolder.getRequestAttributes())
        ).getRequest();

        return new RequestContext(request);
    }


}
