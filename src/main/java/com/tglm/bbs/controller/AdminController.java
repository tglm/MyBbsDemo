package com.tglm.bbs.controller;

import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.exception.ServiceException;
import com.tglm.bbs.permission.Permit;
import com.tglm.bbs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
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


    /**
     * @param username string
     * @return UserInfo
     * @throws ServiceException z找不到用户1006
     */
    @GetMapping("search")
    @Permit(role = "admin")
    public UserInfo search(String username) throws ServiceException {
        return adminService.search(username);
    }

    @GetMapping
    @Permit("admin")
    public Page<UserInfo> list(){
        return adminService.list();
    }




}
