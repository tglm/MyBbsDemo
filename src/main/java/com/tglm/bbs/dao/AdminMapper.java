package com.tglm.bbs.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author mlgt
 * @date 2019/10/4
 */
@Repository
public interface AdminMapper {

    /**
     * 拿到密码进行验证
     *
     * @return password
     */
    @Select("SELECT a.password FROM bbs.admin a WHERE account = #{account};")
    String findPasswordByAccount(String account);

}
