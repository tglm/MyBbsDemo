package com.tglm.bbs.service;

import com.tglm.bbs.dao.AdminMapper;
import com.tglm.bbs.dao.CommentMapper;
import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.dao.UserMapper;
import com.tglm.bbs.dto.SignInfo;
import com.tglm.bbs.entities.Admin;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mlgt
 * @date 2019/10/4
 */
@Service
public class AdminService {

    final UserMapper userMapper;
    final PostMapper postMapper;
    final CommentMapper commentMapper;
    final AdminMapper adminMapper;
    final SessionUtil sessionUtil;
    @Autowired
    public AdminService(UserMapper userMapper, PostMapper postMapper, CommentMapper commentMapper, AdminMapper adminMapper, SessionUtil sessionUtil) {
        this.userMapper = userMapper;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.adminMapper = adminMapper;
        this.sessionUtil = sessionUtil;
    }


    public String killUser(Long userId){
        userMapper.deleteByUserId(userId);
        postMapper.deleteByUserId(userId);
        List<Post> posts = postMapper.findPostByUserId(userId);
        for (Post post:posts) {
            commentMapper.deleteCommentByPostId(post.getPostId());
        }
        return "消除成功";
    }

    public Session login(SignInfo signInfo) throws ServiceException {
        if(adminMapper.findPasswordByAccount(signInfo.getUsername()) == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NO_SUCH_USERNAME,"没有此管理员");
        }
        if(adminMapper.findPasswordByAccount(signInfo.getUsername()).equals(signInfo.getPassword())){
            return sessionUtil.init(new Admin(signInfo.getUsername(),signInfo.getPassword()));
        }
        throw ServiceException.forCodeAndMessage(ServiceException.WRONG_PASSWORD,"密码错误");
    }
}
