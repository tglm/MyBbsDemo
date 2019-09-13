package com.tglm.bbs.dao;

import com.tglm.bbs.entities.Post;
import org.apache.ibatis.annotations.*;
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
     *
     * @param post post
     */
    @Options(useGeneratedKeys = true, keyColumn = "post_id", keyProperty = "postId")
    @Insert("INSERT bbs.post (post_id,content,creator_id,topic,former_post_id,date_create) VALUES (#{postId},#{content},#{creatorId},#{topic},#{formerPostId},#{dateCreate});")
    void savePost(Post post);


    /**
     * 显示全部Topic帖
     *
     * @return 返回Topic帖（分页）
     */
    @Select("SELECT * FROM bbs.post WHERE topic = true;")
    Page<Post> listAll();

    /**
     * 根据id删除帖子
     *
     * @param postId Long
     */
    @Select("DELETE * FROM bbs.post WHERE post_id=#{postId};")
    void deleteByPostId(@Param("post_id") Long postId);

    /**
     * @param post 更改后的贴
     * @param postId 根据Id覆盖
     */
    @Update("UPDATE bbs.post set (post_id,content,creator_id,topic,former_post_id,date_create)=(#{postId},#{content},#{topic},#{formerPostId},#{dateCreate}) where post_id=#{postId};")
    void modifyPostContent(Post post, @Param("post_id") Long postId);


    Post findPostByPostId(Long postId);

    Post findPostByFormerPostId(Long formerPostId);


}
