package com.tglm.bbs.util;

import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.entities.User;

/**
 * @author mlgt
 * @date 2019/9/14
 */
public class InfoUtil {

    /**
     * @param user User
     * @return UserInfo
     */
    public static UserInfo toUserInfo(User user) {
        return new UserInfo(
                user.getUserId(),
                user.getUsername(),
                user.getAvatar()
        );
    }








}
