package com.tglm.bbs.controller;

import com.tglm.bbs.dto.SignInfo;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
import com.tglm.bbs.request.Session;
import com.tglm.bbs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mlgt
 * @date 2019/10/4
 */
@RestController
@RequestMapping("manage")
@ResponseBody
public class AdminController {

    final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Permit("admin")
    @GetMapping("login")
    public Session login(@RequestBody SignInfo signInfo) throws ServiceException {
        return adminService.login(signInfo);
    }

    @Permit("admin")
    @PostMapping("kill")
    public String killUser(Long userId){
        return adminService.killUser(userId);
    }


}
