package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author mlgt
 * @date 2019/9/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
    private String content;
    private Date date;
    private Long commentId;
    private Long formerComment;
    private Long postId;





}
