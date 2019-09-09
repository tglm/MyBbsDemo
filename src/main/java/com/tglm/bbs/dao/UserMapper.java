package com.tglm.bbs.dao;

import com.tglm.bbs.entities.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Repository
public interface UserMapper {




    /**
     * 注册保存User信息
     * @Param User usr
     */
    void saveUser(User user);

    /**
     * 根据用户名查找用户
     * @param username string
     * @return User Entity
     */
    User findByUsername(@Param("username") String username);

    /**
     * 返回所有User信息
     * @return
     */
    Page<User> findAll();

}
