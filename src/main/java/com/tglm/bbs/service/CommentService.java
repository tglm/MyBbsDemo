package com.tglm.bbs.service;

import com.tglm.bbs.dao.CommentMapper;
import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void saveComment(Comment comment){
        commentMapper.saveComment(comment);

    }


    public List<Comment> getComment(Long postId){
        return commentMapper.getComment(postId);
    }

    public String deleteCommentByPostId(Long postId) throws ServiceException {
        if(postId==null){throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        commentMapper.deleteCommentByPostId(postId);
        return "删除成功";

    }


}


