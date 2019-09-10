package com.tglm.bbs.controller;

import com.tglm.bbs.aop.Permit;
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
    public Page<Post> listAll() {
        return postService.listAll();

    }

    @Permit("user")
    @GetMapping("/post")
    public void post(Post post) {
        postService.post(post);
    }




}
