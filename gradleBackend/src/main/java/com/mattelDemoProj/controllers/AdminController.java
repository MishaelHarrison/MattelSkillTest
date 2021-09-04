package com.mattelDemoProj.controllers;

import com.mattelDemoProj.dto.UserDTO;
import com.mattelDemoProj.sevices.AdminService;
import com.mattelDemoProj.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("grant")
    public void grantAccess(@RequestBody String user, Principal principal){
        adminService.grantAccess(user, principal.getName());
    }

    @GetMapping("requests")
    public List<UserDTO> getRequests(){
        return userService.getRequests();
    }

}
