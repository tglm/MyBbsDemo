package com.tglm.bbs.service;

import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Service
public class PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }


    public void post(Post post){
        postMapper.savePost(post);


    }

    public Page<Post> listAll(){


        return postMapper.listAll();

    }


}
