package com.tglm.bbs.service;

import com.tglm.bbs.Util.InfoUtil;
import com.tglm.bbs.Util.PageArgs;
import com.tglm.bbs.dao.UserMapper;
import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.entities.User;
import com.tglm.bbs.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author mlgt
 * @date 2019/9/22
 */
@Service
public class AdminService {

    final UserMapper userMapper;

    @Autowired
    public AdminService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @return List<UserInfo></>
     */
    public Page<UserInfo> list() {
        return userMapper.findAll(new PageArgs(1,5)).map(InfoUtil::toUserInfo);
    }

    /**
     * @param username string
     * @return userinfo
     * @throws ServiceException 找不到用户
     */
    public UserInfo search(String username) throws ServiceException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw ServiceException.forCodeAndMessage(ServiceException.NO_SUCH_USERNAME, "用户不存在");
        }
        return InfoUtil.toUserInfo(user);

    }






}
