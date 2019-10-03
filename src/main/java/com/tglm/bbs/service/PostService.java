package com.tglm.bbs.service;

import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.dto.NewPostInfo;
import com.tglm.bbs.dto.PostInfo;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.util.InfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;

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


    public String post(NewPostInfo newPostInfo) throws ServiceException, ParseException {
        if(newPostInfo == null){
        throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
    }
        Post post = new Post(newPostInfo);

        postMapper.savePost(post);
        return "发帖成功";
    }

    public Page<PostInfo> listAll() throws ServiceException {

        if(postMapper.listAll() == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }

        Page<Post> posts = postMapper.listAll();
        return posts.map(InfoUtil::toPostInfo);
    }

    public String updatePost(NewPostInfo newPostInfo) throws ServiceException, ParseException {
        if(newPostInfo == null || newPostInfo.getContent() == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        Post post = new Post(newPostInfo);
        postMapper.modifyPostContent(post,post.getPostId());
        return "更新成功";

    }

    public String deletePost(Long postId){

        Post post = postMapper.findPostByPostId(postId);

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
