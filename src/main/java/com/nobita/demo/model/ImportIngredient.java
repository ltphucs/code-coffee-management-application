package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImportIngredient {
    private Long id;
    private LocalDateTime dateJoin;
    private Ingredient ingredient;
    private Long quantity;
    private Long totalQuantity;
    private Long price;
    private Long totalPrice;
    private String comment;
}
