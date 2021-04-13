package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Unit {
    private Long id;

    @NotBlank(message = "Tên đơn vị không được để trống")
    private String name;
    private String comment;
}
