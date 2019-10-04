package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


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
    private Long formerComment;
    private Long postId;






}
