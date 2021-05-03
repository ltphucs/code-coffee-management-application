package com.nobita.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Bill {
    private Long idOrder;
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateJoin;
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateExport;
    private String nameTable;
    private Long totalPrice;
}
