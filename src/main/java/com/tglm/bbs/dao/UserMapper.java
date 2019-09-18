package com.tglm.bbs.dao;

import com.tglm.bbs.Util.PageArgs;
import com.tglm.bbs.entities.User;
import org.apache.ibatis.annotations.*;
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
     *
     * @Param User user
     */
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    @Insert("INSERT INTO bbs.user (password,username,avatar) VALUES (#{password},#{username},#{avatar});")
    void saveUser(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username string
     * @return User Entity
     */
    @Select("SELECT * FROM bbs.user WHERE username = #{username};")
    User findByUsername(@Param("username") String username);

    /**
     * 返回所有User信息
     * @param pageArgs (Integer pageNum,Integer pageSize)
     * @return Page<User> 分页User实体
     */
    @Select("SELECT * FROM bbs.user")
    Page<User> findAll(PageArgs pageArgs);

    @Update("UPDATE bbs.user SET username = #{username} ")
    void updatePasswordByUsername(String password,String username);

}
