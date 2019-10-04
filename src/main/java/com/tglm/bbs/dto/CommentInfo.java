package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;


/**
 * @author mlgt
 * @date 2019/9/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  CommentInfo {
    private String content;
    @Nullable
    private Date date;
    @Nullable
    private Long commentId;
    @Nullable
    private Long formerComment;
    private Long postId;






}
