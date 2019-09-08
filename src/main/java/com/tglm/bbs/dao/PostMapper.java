package com.tglm.bbs.dao;

import com.tglm.bbs.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Repository
public interface PostMapper {

    /**
     * 存帖子
     * @param post post
     */
    void savePost(Post post);


    /**
     * 显示全部帖子
     * @return 返回帖子（分页）
     */
    Page<Post> listAll();

}
