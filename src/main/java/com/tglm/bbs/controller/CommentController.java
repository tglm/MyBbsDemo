package com.tglm.bbs.controller;

import com.github.pagehelper.Page;
import com.tglm.bbs.dto.CommentInfo;
import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.security.authCheck.Creator;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.refresh.Refresh;
import com.tglm.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController
@ResponseBody
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("saveComment")
    @Refresh
    public String saveComment(@RequestBody CommentInfo commentInfo) throws ParseException, ServiceException {
         return commentService.saveComment(commentInfo);
    }

    @GetMapping("getComment")
    public Page<Comment> getComment(Long postId) throws ServiceException {
        return commentService.getCommentInfo(postId);
    }

    @PostMapping("deleteComment")
    @Creator
    @Refresh
    public String deleteComment(Long postId) throws ServiceException {
        return commentService.deleteCommentByPostId(postId);
    }
}
