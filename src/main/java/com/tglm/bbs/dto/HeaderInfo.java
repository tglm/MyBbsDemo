package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author mlgt
 * @date 2019/8/30
 */
@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class HeaderInfo {
    String ip;
    String sessionId;
}
