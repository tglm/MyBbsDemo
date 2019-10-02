package com.tglm.bbs.service;

import com.tglm.bbs.dao.UserMapper;
import com.tglm.bbs.dto.SignInfo;
import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.entities.User;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.upload.Upload;
import com.tglm.bbs.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@Service
public class UserService {
    @Value("D:/test/bbs/avatars")
    private String avatarRootPath;
    private SessionUtil sessionUtil;
    private UserMapper userMapper;
    private final RedisUtil redisUtil;

    @Autowired
    public UserService(SessionUtil sessionUtil, UserMapper userMapper, RedisUtil redisUtil) {
        this.sessionUtil = sessionUtil;
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }



    public String signUp(SignInfo signInfo) throws ServiceException {


        String regex = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";

        if (userMapper.findByUsername(signInfo.getUsername()) != null) {
            throw ServiceException.forCodeAndMessage(ServiceException.USERNAME_HAS_EXISTED, "用户名已存在");

        }
        if (!signInfo.getUsername().matches(regex)) {
            throw ServiceException.forCodeAndMessage(ServiceException.WRONG_FORMAT, "用户名格式错误");
        }


        userMapper.saveUser(new User(signInfo.getUsername(),null, signInfo.getPassword(),null));
        return "注册成功";

    }


    public Session login(SignInfo signInfo) throws ServiceException {
        String username = signInfo.getUsername();
        if (userMapper.findByUsername(username) == null) {
            throw ServiceException.forCodeAndMessage(ServiceException.NO_SUCH_USERNAME, "用户名不存在");
        }
        Long id = userMapper.findByUsername(username).getUserId();
        User user = new User(username, id, signInfo.getPassword(), null);

        if (user.getPassword().equals(userMapper.findByUsername(user.getUsername()).getPassword())) {

            return sessionUtil.init(user);
        }

        throw ServiceException.forCodeAndMessage(ServiceException.WRONG_PASSWORD, "密码错误");


    }


    @Upload(file = "avatar",extension = {"jpg","png"},maxfileSize = 1024*1024*5,maxfile = 1)
    public String uploadAvatar(MultipartFile file) throws IOException, ServiceException {
        String username = redisUtil.getSession(RequestUtil.getHeaderInfo().getSessionId()).getUsername();
        new File(avatarRootPath + File.separator + username).delete();
        String avatarPath = avatarRootPath + File.separator + username;
        File avatarFile = new File(avatarPath);
        if (!avatarFile.exists()) {
            avatarFile.mkdirs();
        }
        String avatar = avatarPath + "/" + file.getOriginalFilename();
        file.transferTo(new File(avatar));
        userMapper.updateAvatarByUsername(avatar,username);
        return "上传成功";
    }

    public String resetPassword(SignInfo signInfo) throws ServiceException {

        if (userMapper.findByUsername(signInfo.getUsername()) == null) {
            throw ServiceException.forCodeAndMessage(ServiceException.NO_SUCH_USERNAME, "用户不存在");
        }
            userMapper.updatePasswordByUsername(signInfo.getUsername(), signInfo.getPassword());
            return "重置密码成功";

    }

    public void getAvatar(Long userId) throws IOException, ServiceException {
        String avatarPath = userMapper.findByUserId(userId).getAvatar();
        FileUtil.responseWithFile(avatarPath);

    }
    /**
     * @param username string
     * @return userInfo
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
