package com.nobita.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private LocalDateTime dateJoin;
    private LocalDateTime dateExport;
    private Table table;
    private Long totalAllPrice;
    private Account account;
}
