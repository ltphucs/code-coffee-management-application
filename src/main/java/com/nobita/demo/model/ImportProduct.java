package com.nobita.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ImportProduct {
    private Long id;
    private LocalDateTime dateJoin;
    private Product product;
    private int quantity;
    private Long price;
    private Long totalPrice;
    private String comment;
}
