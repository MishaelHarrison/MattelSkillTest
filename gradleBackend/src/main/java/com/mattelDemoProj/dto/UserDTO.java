package com.mattelDemoProj.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String fName;
    private String lName;
    private String contactNumber;
    private String email;
    private String address;
    private String businessJustification;

    private String password;

}
