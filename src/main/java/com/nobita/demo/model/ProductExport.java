package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductExport {
    private Long id;
    private LocalDateTime dateExport;
    private String nameProduct;
    private Long quantity;
    private Long idProduct;
}
