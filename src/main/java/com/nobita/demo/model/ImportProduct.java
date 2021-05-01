package com.nobita.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class ImportProduct {
    private Long id;

    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateJoin;
    private Product product;

    @Min(value = 1,message = "Số lượng không thể bé hơn 1")
    private int quantity;

    @Min(value = 1,message = "Giá không thể bé hơn 1")
    private Long price;
    private Long totalPrice;
    private String comment;
}
