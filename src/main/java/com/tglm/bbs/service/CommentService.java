package com.tglm.bbs.service;

import com.github.pagehelper.Page;
import com.tglm.bbs.dao.CommentMapper;
import com.tglm.bbs.dto.CommentInfo;
import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.request.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Service
public class CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    public String saveComment(CommentInfo commentInfo) throws ParseException, ServiceException {
        if(commentInfo.getContent() == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"评论不能为空");
        }
        Comment comment = new Comment(commentInfo);
        commentMapper.saveComment(comment);
        return "成功";
    }


    public Page<Comment> getCommentInfo(Long postId) throws ServiceException {

        Page<Comment> comments = commentMapper.getComment(postId, ThreadContext.getPageArgs());
        if (comments == null) {
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        return comments;
    }

    public String deleteCommentByPostId(Long postId) throws ServiceException {
        if (postId == null) {
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        commentMapper.deleteCommentByPostId(postId);
        return "删除成功";

    }


}


