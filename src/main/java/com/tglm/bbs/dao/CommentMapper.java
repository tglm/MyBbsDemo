package com.tglm.bbs.dao;

import com.tglm.bbs.entities.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Repository
public interface CommentMapper {

    /**
     * @param comment
     */
    void saveComment(@Param("comment") Comment comment);

    /**
     * 拿到Comment
     * @param postId long
     * @return 
     */
    List<Comment> getComment(@Param("postId") Long postId);


}
