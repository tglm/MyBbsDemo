package com.tglm.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    public String signUp(@RequestBody JSONObject signInfoParam) throws ServiceException {
        JSONObject signInfoJson = signInfoParam.getJSONObject("signInfo");
        SignInfo signInfo = JSON.toJavaObject(signInfoJson,SignInfo.class);
        return userService.signUp(signInfo);
    }

    @PostMapping("/login")
    public Session login(@RequestBody JSONObject signInfoParam) throws ServiceException {
        JSONObject signInfoJson = signInfoParam.getJSONObject("signInfo");
        SignInfo signInfo = JSON.toJavaObject(signInfoJson,SignInfo.class);
        return userService.login(signInfo);


    }

    @Permit("user")
    @PostMapping("uploadAvatar")
    public String uploadAvatar (@RequestBody MultipartFile file) throws IOException {

        return userService.uploadAvatar(file);

    }

}
