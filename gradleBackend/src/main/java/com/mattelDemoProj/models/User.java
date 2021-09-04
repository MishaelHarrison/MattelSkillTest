package com.mattelDemoProj.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String email;

    //only for receiving
    private String password;

    private String fName;
    private String lName;
    private String contactNumber;
    private String address;
    private String businessJustification;

}
