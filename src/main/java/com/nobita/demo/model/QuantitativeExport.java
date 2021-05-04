package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuantitativeExport {
    private Long id;
    private Long idOrder;
    private LocalDateTime dateExport;
    private String nameProduct;
    private String nameIngredient;
    private Double quantity;
}
