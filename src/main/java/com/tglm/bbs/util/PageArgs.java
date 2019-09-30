package com.tglm.bbs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mlgt
 * @date 2019/9/13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageArgs {
    private Integer pageNum;
    private Integer pageSize;
}
