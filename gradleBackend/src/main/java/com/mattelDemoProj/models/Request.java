package com.mattelDemoProj.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int by;
    private Timestamp expiration;

    public Request(int user){
        by = user;
        expiration = Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS));
    }

}
