package com.tglm.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    Long userId;
    String username;
    String avatar;


}
