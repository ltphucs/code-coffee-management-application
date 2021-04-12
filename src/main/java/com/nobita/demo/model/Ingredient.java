package com.nobita.demo.model;

import lombok.Data;

@Data
public class Ingredient {
    private Long id;
    private String name;
    private Unit unit;
    private String comment;
}
