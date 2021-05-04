package com.nobita.demo.model;

import lombok.Data;

@Data
public class Quantitative {
    private Product product;
    private Ingredient ingredient;
    private Double quantity;
}
