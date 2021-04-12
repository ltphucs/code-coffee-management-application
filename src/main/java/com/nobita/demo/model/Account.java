package com.nobita.demo.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String name;
    private String password;
    private Authorization authorization;
}
