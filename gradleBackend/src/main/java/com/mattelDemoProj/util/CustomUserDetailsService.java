package com.mattelDemoProj.util;

import com.mattelDemoProj.exceptions.EntityNotFound;
import com.mattelDemoProj.models.Admin;
import com.mattelDemoProj.models.User;
import com.mattelDemoProj.repo.AdminRepository;
import com.mattelDemoProj.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String username;
        String password;
        String role = "admin";
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if(admin != null){
            username = admin.getEmail();
            password = admin.getPassword();
        }else {
            User user = userRepository.findByEmail(email).orElse(null);
            if(user == null) throw new EntityNotFound();
            role = "user";
            username = user.getEmail();
            password = user.getPassword();
        }
        return new org.springframework.security.core.userdetails.User(username, password,
                Stream.of(role).map(x-> (GrantedAuthority) () -> x).collect(Collectors.toList()));
    }
}
