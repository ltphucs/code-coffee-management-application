package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Ingredient {
    private Long id;

    @Size(min = 3,message = "Tên thành phần không thể bé hơn 3 ký tự")
    private String name;
    private Unit unit;
    private String comment;
}
