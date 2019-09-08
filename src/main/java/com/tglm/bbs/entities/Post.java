package com.tglm.bbs.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Data
public class Post {

    Long postId;
    Long creatorId;
    Long formerPostId;
    String content;
    Boolean topic;
    Timestamp dateCreate;

    public Post(Long creatorId, Long formerPostId, String content) {
        this.creatorId = creatorId;
        this.formerPostId = formerPostId;
        this.content = content;
        this.topic = formerPostId == null;

    }
}
