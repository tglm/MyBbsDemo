package com.tglm.bbs.Util;

import com.tglm.bbs.dto.SignUpInfo;
import com.tglm.bbs.entities.User;

/**
 * @author mlgt
 * @date 2019/9/8
 */
public class InfoUtil {



    public static User toUser(SignUpInfo signUpInfo){

        return new User(signUpInfo.getUsername(),signUpInfo.getPassword());

    }





}


