package com.nobita.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class ImportIngredient {
    private Long id;

    @JsonFormat(pattern = "HH:mm:ss MM/dd/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateJoin = LocalDateTime.now();

    private Ingredient ingredient;

    @Min(value = 1,message = "Số lượng không được bé hơn 1")
    private Long quantity;

    @Min(value = 1,message = "Giá không thể bé hơn 1")
    private Long price;

    private Long totalPrice;
    private String comment;

    @Override
    public String toString() {
        return "ImportIngredient{" +
                "id=" + id +
                ", dateJoin=" + dateJoin +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", comment='" + comment + '\'' +
                '}';
    }
}
