package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mlgt
 * @date 2019/8/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderInfo {
    String ip;
    String sessionId;
}
