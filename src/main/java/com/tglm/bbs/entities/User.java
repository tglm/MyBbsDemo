package com.tglm.bbs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    Long userId;
    String password;
    String username;
    String avatar;
    String role;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
        this.role="user";
    }
}
