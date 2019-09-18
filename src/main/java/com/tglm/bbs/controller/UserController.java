package com.tglm.bbs.controller;

import com.tglm.bbs.dto.SignInfo;
import com.tglm.bbs.entities.Session;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
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
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignInfo signInfo) throws ServiceException {

        return userService.signUp(signInfo);
    }

    @PostMapping("/login")
    public Session login(@RequestBody SignInfo signInfo) throws ServiceException {

        return userService.login(signInfo);


    }

    @Permit("user")
    @PostMapping("uploadAvatar")
    public String uploadAvatar (@RequestBody MultipartFile file) throws IOException {

        return userService.uploadAvatar(file);

    }

}
