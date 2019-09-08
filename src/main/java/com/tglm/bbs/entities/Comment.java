package com.tglm.bbs.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Data
public class Comment {

    int commentId;
    int formerComment;
    Long postId;
    Timestamp date;
    String content;



}
