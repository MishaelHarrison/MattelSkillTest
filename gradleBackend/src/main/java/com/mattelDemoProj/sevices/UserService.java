package com.mattelDemoProj.sevices;

import com.mattelDemoProj.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO register (UserDTO newUser);
    UserDTO getData(String email);
    List<UserDTO> getRequests();
    String access(String email);

}
