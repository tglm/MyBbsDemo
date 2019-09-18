package com.tglm.bbs.service;

import com.tglm.bbs.Util.InfoUtil;
import com.tglm.bbs.dao.CommentMapper;
import com.tglm.bbs.dto.CommentInfo;
import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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


    public void saveComment(CommentInfo commentInfo) throws ParseException, ServiceException {
        if(commentInfo.getContent() == null){
            throw ServiceException.forCodeAndMessage(ServiceException.NULL_PARAMETER_ERROR,"评论不能为空");
        }
        Comment comment = new Comment(commentInfo);
        commentMapper.saveComment(comment);

    }


    public List<CommentInfo> getCommentInfo(Long postId) throws ServiceException {
        List<CommentInfo> commentInfoList = null;
        List<Comment> comments = commentMapper.getComment(postId);
        if (comments == null) {
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        for (Comment comment : comments) {
            commentInfoList.add(InfoUtil.toCommentInfo(comment));
        }
        return commentInfoList;
    }

    public String deleteCommentByPostId(Long postId) throws ServiceException {
        if (postId == null) {
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        commentMapper.deleteCommentByPostId(postId);
        return "删除成功";

    }


}


