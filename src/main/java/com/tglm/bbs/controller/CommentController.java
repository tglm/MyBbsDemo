package com.tglm.bbs.controller;

import com.tglm.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

    public void saveComment(Comment comment){
        commentService.saveComment(comment);
    }


}
