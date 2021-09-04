package com.mattelDemoProj.controllers;

import com.mattelDemoProj.dto.UserDTO;
import com.mattelDemoProj.sevices.AdminService;
import com.mattelDemoProj.sevices.UserService;
import com.mattelDemoProj.util.AuthRequest;
import com.mattelDemoProj.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class BaseController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public BaseController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch (Exception e){
            throw new Exception("Invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/role")
    public String getRole(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority();
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO user){
        return userService.register(user);
    }

}
