package com.tglm.bbs.controller;

import com.tglm.bbs.dto.LoginInfo;
import com.tglm.bbs.dto.SignUpInfo;
import com.tglm.bbs.entities.Session;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(@RequestBody SignUpInfo signUpInfo) throws ServiceException {
        return userService.signUp(signUpInfo);
    }

    @GetMapping("/login")
    public Session login(LoginInfo loginInfo) throws ServiceException {
        return userService.login(loginInfo);


    }

    @PostMapping("uploadAvatar")
    public void uploadAvatar (@RequestBody MultipartFile file){

        userService.uploadAvatar(file);

    }






}
