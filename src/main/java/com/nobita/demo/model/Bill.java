package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bill {
    private Long idOrder;
    private LocalDateTime dateJoin;
    private LocalDateTime dateExport;
    private String nameTable;
    private Long totalPrice;
}
