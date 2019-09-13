package com.tglm.bbs.controller;

import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController("/post")
public class PostController {
    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/list")
    public Page<Post> listAll() throws ServiceException {
        return postService.listAll();

    }

    @Permit("user")
    @GetMapping("/post")
    public String post(Post post) throws ServiceException {
        return postService.post(post);
    }


    @Permit("user")
    @GetMapping("/updatePost")
    public String updatePost(Post post) throws ServiceException {
        return postService.updatePost(post);

    }

    @GetMapping("/deletPost")
    public String deletePost(Post post) throws ServiceException {
        return postService.deletePost(post);
    }

}
