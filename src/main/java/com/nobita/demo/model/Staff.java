package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Staff {
    private Long id;
    private LocalDate dateJoin;
    private String fullname;
    private String gender;
    private Position position;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private String username;
    private String password;
}
