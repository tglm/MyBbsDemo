package com.tglm.bbs.controller;

import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/saveComment")
    public void saveComment(Comment comment){
        commentService.saveComment(comment);
    }

    @GetMapping("/getComment")
    public List<Comment> getComment(Long postId){
        return commentService.getComment(postId);

    }

    @GetMapping("/deletePost")
    public String deleteComment(Long postId) throws ServiceException {
        return commentService.deleteCommentByPostId(postId);
    }
}
