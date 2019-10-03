package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * @author mlgt
 * @date 2019/10/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPostInfo {
    private
    private String content;
    private boolean topic;
    @Nullable
    private Long formerPostId;

}
