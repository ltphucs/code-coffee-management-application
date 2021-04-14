package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Authorization {
    private Long id;

    private String name;
}
