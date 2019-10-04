package com.tglm.bbs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tglm.bbs.Page.PageArgs;
import com.tglm.bbs.dao.PostMapper;
import com.tglm.bbs.dto.ModifiedPostInfo;
import com.tglm.bbs.dto.NewPostInfo;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.request.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PageInfo<Post> listAll() throws ServiceException {

        PageArgs args = ThreadContext.getPageArgs();
        if(postMapper.listAll(args) == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }

        Page<Post> posts = postMapper.listAll(args);

        return posts.toPageInfo();
    }

    public String updatePost(ModifiedPostInfo modifiedPostInfo) throws ServiceException, ParseException {
        if(modifiedPostInfo == null || modifiedPostInfo.getContent() == null){
            throw ServiceException.forCode(ServiceException.NULL_PARAMETER_ERROR);
        }
        Post post = new Post(modifiedPostInfo);
//        postMapper.modifyPostContent(post,post.getPostId());
        postMapper.modifyPostContent(post);
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
