package com.mattelDemoProj.sevices;

public interface AdminService {

    void grantAccess(String email, String username);
    void requestAccess (String email);

}
