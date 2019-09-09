package com.tglm.bbs.dao;

import com.tglm.bbs.entities.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Repository
public interface CommentMapper {

    /**
     * @param comment
     */
    void SaveComment(@Param("comment") Comment comment);

    /**
     * 拿到Comment
     * @param commentid long
     * @return 
     */
    Comment getComment(@Param("comment_id") long commentId);


}
