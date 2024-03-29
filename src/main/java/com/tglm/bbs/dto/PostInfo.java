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
@NoArgsConstructor
@AllArgsConstructor
public class PostInfo {
    @Nullable
    private Long postId;
    private String content;
    private Long creatorId;
    private boolean topic;
    private Long formerPostId;
    private Date dateCreate;


}
