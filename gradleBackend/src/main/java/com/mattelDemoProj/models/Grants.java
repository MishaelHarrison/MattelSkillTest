package com.mattelDemoProj.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int to;
    private int by;

    public Grants(int to, int by) {
        this.to = to;
        this.by = by;
    }

}
