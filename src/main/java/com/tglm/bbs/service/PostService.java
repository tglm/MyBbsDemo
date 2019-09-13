package com.tglm.bbs.service;

import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.exception.ServiceException;
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


    public String post(Post post) throws ServiceException {
        if(post==null){throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);}
        postMapper.savePost(post);
        return "发帖成功";
    }

    public Page<Post> listAll() throws ServiceException {

        if(postMapper.listAll() == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        return postMapper.listAll();

    }

    public String updatePost(Post post) throws ServiceException {


        if(post == null || post.getPostId() == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        postMapper.modifyPostContent(post,post.getPostId());
        return "更新成功";

    }

    public String deletePost(Post post) throws ServiceException {
        if(post == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        if(post.isTopic()){
            Long tempId = post.getPostId();
            while (postMapper.findPostByPostId(tempId) != null){
                postMapper.deleteByPostId(tempId);
                Post temPost = postMapper.findPostByFormerPostId(tempId);
                if(temPost == null){
                    break;
                }
                tempId = temPost.getPostId();
            }
        }
        postMapper.deleteByPostId(post.getPostId());
        return "删除成功";

    }


}
