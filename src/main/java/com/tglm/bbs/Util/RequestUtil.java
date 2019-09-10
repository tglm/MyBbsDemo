package com.tglm.bbs.Util;

import com.example.qaq.po.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/8/30
 */
@Component
public class RequestUtil {

    public Header getHeaderInfo() {
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
        return new Header(ip, sessionId);
    }

}
