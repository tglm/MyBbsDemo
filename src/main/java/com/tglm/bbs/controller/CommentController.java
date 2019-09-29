package com.tglm.bbs.controller;

import com.tglm.bbs.dto.CommentInfo;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.refresh.Refresh;
import com.tglm.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController("comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("saveComment")
    @Refresh
    public void saveComment(@RequestBody CommentInfo commentInfo) throws ParseException, ServiceException {
         commentService.saveComment(commentInfo);
    }

    @GetMapping("getComment")
    public List<CommentInfo> getComment(Long postId) throws ServiceException {
        return commentService.getCommentInfo(postId);

    }

    @GetMapping("deleteComment")
    @Refresh
    public String deleteComment(Long postId) throws ServiceException {
        return commentService.deleteCommentByPostId(postId);
    }
}
