package com.mattelDemoProj.controllers;

import com.mattelDemoProj.dto.UserDTO;
import com.mattelDemoProj.sevices.AdminService;
import com.mattelDemoProj.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public UserController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getData(Principal principal){
        return userService.getData(principal.getName());
    }

    @GetMapping("access")
    public String access(Principal principal){
        return userService.access(principal.getName());
    }

    @PostMapping("access")
    public void requestAccess(Principal principal){
        adminService.requestAccess(principal.getName());
    }

}
