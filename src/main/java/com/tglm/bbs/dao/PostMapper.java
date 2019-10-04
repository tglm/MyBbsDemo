package com.tglm.bbs.dao;

import com.github.pagehelper.Page;
import com.tglm.bbs.Page.PageArgs;
import com.tglm.bbs.entities.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Insert("INSERT INTO bbs.post (content,creator_id,topic,former_post_id,date_create) VALUES (#{content},#{creatorId},#{topic},#{formerPostId},#{dateCreate});")
    void savePost(Post post);


    /**
     * 显示全部Topic帖
     *
     * @return 返回Topic帖（分页）
     */
    @Select("SELECT * FROM bbs.post WHERE topic = true order by post_id desc;")
    Page<Post> listAll(PageArgs pageArgs);

    /**
     * 根据id删除帖子
     *
     * @param postId Long
     */
    @Select("DELETE FROM bbs.post WHERE post_id = #{postId};")
    void deleteByPostId(@Param("post_id") Long postId);

    /**
     * @param post 更改后的贴
     */
    @Update("UPDATE bbs.post set (post_id,content,creator_id,former_post_id,date_create) = (#{postId},#{content},#{creatorId},#{formerPostId},#{dateCreate}) where post_id=#{postId};")
    void modifyPostContent(Post post);

    //没测试--------------------------------------------------------------------------------

    @Select("SELECT * FROM bbs.post WHERE creator_id = #{userId};")
    List<Post> findPostByUserId(Long userId);

    @Select("SELECT * FROM bbs.post WHERE post_id = #{postId};")
    Post findPostByPostId(Long postId);

    @Select("SELECT * FROM bbs.post WHERE former_post_id = #{formerPostId};")
    Post findPostByFormerPostId(Long formerPostId);

    @Delete("DELETE FROM bbs.post WHERE creator_id = #{userId};")
    void deleteByUserId(Long userId);


}
