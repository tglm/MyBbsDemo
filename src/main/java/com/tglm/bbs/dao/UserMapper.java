package com.tglm.bbs.dao;

import com.tglm.bbs.entities.User;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Repository
public interface UserMapper {


    /**
     * @return User
     */
    User getUser();

    /**
     * 注册保存User信息
     */
    void saveUser(User user);

    User findByUsername(String username);

}
