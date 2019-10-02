package com.tglm.bbs.controller;

import com.tglm.bbs.dto.PostInfo;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
import com.tglm.bbs.refresh.Refresh;
import com.tglm.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController("post")
public class PostController {
    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("list")
    public Page<PostInfo> listAll() throws ServiceException {
        return postService.listAll();
    }

    @Permit("user")
    @PostMapping("postPost")
    @Refresh
    public String post(@RequestBody PostInfo postInfo) throws ServiceException, ParseException {
        return postService.post(postInfo);
    }


    @Permit("user")
    @PostMapping("updatePost")
    @Refresh
    public String updatePost(@RequestBody PostInfo postInfo) throws ServiceException, ParseException {

        return postService.updatePost(postInfo);

    }

    @Permit("admin")
    @GetMapping("deletePost")
    public String deletePost(Long postId){
        return postService.deletePost(postId);
    }


}
