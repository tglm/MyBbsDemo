package com.tglm.bbs.security.authCheck;

import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.request.ThreadContext;
import com.tglm.bbs.security.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mlgt
 * @date 2019/10/5
 */

@Aspect
@Component
public class AuthCheckAspect {

    final PostMapper postMapper;

    @Autowired
    public AuthCheckAspect(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Before("@annotation(creator)")
    public void check(Creator creator) throws ServiceException {
        Long userId = ThreadContext.getOperator().getUserId();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String postIdStr = request.getParameter("postId");
        Long postId = Long.parseLong(postIdStr);
        Long creatorId = postMapper.findPostByPostId(postId).getCreatorId();
        if(!userId.equals(creatorId)){
            throw ServiceException.forCodeAndMessage(ServiceException.EXCEED_AUTHORITY,"没有权限删除不属于你的帖子");
        }
    }


}
