package com.tglm.bbs.service;

import com.tglm.bbs.dao.CommentMapper;
import com.tglm.bbs.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        commentMapper.SaveComment(comment);

    }


}
