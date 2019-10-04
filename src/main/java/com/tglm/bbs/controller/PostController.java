package com.tglm.bbs.controller;

import com.github.pagehelper.PageInfo;
import com.tglm.bbs.dto.ModifiedPostInfo;
import com.tglm.bbs.dto.NewPostInfo;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
import com.tglm.bbs.refresh.Refresh;
import com.tglm.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController
@ResponseBody
@RequestMapping("post")
public class PostController {
    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("list")
    public PageInfo<Post> listAll() throws ServiceException {
        return postService.listAll();
    }

    @Permit("user")
    @PostMapping("postPost")
    @Refresh
    public String post(@RequestBody NewPostInfo newPostInfo) throws ServiceException, ParseException {
        return postService.post(newPostInfo);
    }


    @Permit("user")
    @PostMapping("updatePost")
    @Refresh
    public String updatePost(@RequestBody ModifiedPostInfo modifiedPostInfo) throws ServiceException, ParseException {

        return postService.updatePost(modifiedPostInfo);

    }

    @Permit("admin")
    @GetMapping("deletePost")
    public String deletePost(Long postId){
        return postService.deletePost(postId);
    }


}
