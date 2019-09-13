package com.tglm.bbs.service;

import com.tglm.bbs.Util.SessionUtil;
import com.tglm.bbs.dao.UserMapper;
import com.tglm.bbs.dto.LoginInfo;
import com.tglm.bbs.dto.SignUpInfo;
import com.tglm.bbs.entities.Session;
import com.tglm.bbs.entities.User;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.upload.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Service
public class UserService {
    private SessionUtil sessionUtil;
    private UserMapper userMapper;
    public UserService(SessionUtil sessionUtil, UserMapper userMapper) {
        this.sessionUtil = sessionUtil;
        this.userMapper = userMapper;
    }

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String signUp(SignUpInfo signUpInfo) throws ServiceException {

        String role = "user";
        signUpInfo.setRole(role);


        String regex = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";

        if (userMapper.findByUsername(signUpInfo.getUsername()) != null) {
            throw ServiceException.forCodeAndMessage(ServiceException.USERNAME_HAS_EXISTED, "用户名已存在");

        }
        if (!signUpInfo.getUsername().matches(regex)) {
            throw ServiceException.forCodeAndMessage(ServiceException.WRONG_FORMAT, "用户名格式错误");
        }


        userMapper.saveUser(new User(signUpInfo.getUsername(),null,signUpInfo.getPassword(),null));
        return "注册成功";

    }


    public Session login(LoginInfo loginInfo) throws ServiceException {
        String username = loginInfo.getUsername();
        if (userMapper.findByUsername(username) == null) {
            throw ServiceException.forCodeAndMessage(ServiceException.NO_SUCH_USERNAME, "用户名不存在");
        }
        Long id = userMapper.findByUsername(username).getUserId();
        User user = new User(username, id, loginInfo.getPassword(), null);

        if (user.getPassword().equals(userMapper.findByUsername(user.getUsername()).getPassword())) {

            return sessionUtil.init(user);
        }

        throw ServiceException.forCodeAndMessage(ServiceException.WRONG_PASSWORD, "密码错误");


    }


    @Upload(file = "avatar",maxfileSize = 1024*1024*5,maxfile = 1)
    public String uploadAvatar(MultipartFile file){



    }

}
