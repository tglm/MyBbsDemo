package com.tglm.bbs.dao;

import com.github.pagehelper.Page;
import com.tglm.bbs.Page.PageArgs;
import com.tglm.bbs.entities.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Repository
public interface CommentMapper {

    /**
     * 上传评论
     *
     * @param comment Comment
     */
    @Options(useGeneratedKeys = true, keyColumn = "comment_id", keyProperty = "commentId")
    @Insert("INSERT INTO bbs.comment(content,date,former_comment,post_id) VALUES (#{content},#{date},#{formerComment},#{postId});")
    void saveComment(Comment comment);

    /**
     * 拿到Comment
     *
     * @param postId Long
     * @return 该帖子下所有评论
     */
    @Select("SELECT * FROM bbs.comment WHERE post_id = #{postId} by comment_id desc;")
    Page<Comment> getComment(@Param("postId") Long postId, PageArgs pageArgs);

    /**
     * 删除帖子下的所有评论
     *
     * @param postId Long
     */
    @Delete("DELETE FROM bbs.comment WHERE post_id = #{postId};")
    void deleteCommentByPostId(@Param("postId") Long postId);
}
