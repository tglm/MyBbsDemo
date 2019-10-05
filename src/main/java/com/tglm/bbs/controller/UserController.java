package com.tglm.bbs.controller;

import com.tglm.bbs.dto.SignInfo;
import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.refresh.Refresh;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.security.permission.Permit;
import com.tglm.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author mlgt
 * @date 2019/9/8
 */
@RestController
@ResponseBody
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signUp")
    public String signUp(@RequestBody SignInfo signInfo) throws ServiceException {

        return userService.signUp(signInfo);
    }

    @PostMapping("login")
    public Session login(@RequestBody SignInfo signInfo) throws ServiceException {

        return userService.login(signInfo);

    }

    @Permit("user")
    @Refresh
    @PostMapping("uploadAvatar")
    public String uploadAvatar (@RequestBody MultipartFile file) throws IOException, ServiceException {

        return userService.uploadAvatar(file);

    }

    @GetMapping("getAvatar")
    @Refresh
    public void getAvatar(@RequestParam Long userId) throws IOException, ServiceException {
        userService.getAvatar(userId);
    }

    @Permit("user")
    @Refresh
    @PostMapping("resetPassword")
    public String resetPassword(@RequestBody SignInfo signInfo) throws ServiceException {
        return userService.resetPassword(signInfo);
    }


    @GetMapping("search")
    public UserInfo search(String username) throws ServiceException {
        return userService.search(username);

    }

}
