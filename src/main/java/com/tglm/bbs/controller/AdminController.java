package com.tglm.bbs.controller;

import com.tglm.bbs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mlgt
 * @date 2019/9/22
 */
@RestController("admin")
public class AdminController {

    final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }






}
