package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class ImportIngredient {
    private Long id;
    private LocalDateTime dateJoin;
    private Ingredient ingredient;

    @Min(value = 1,message = "Số lượng không được bé hơn 1")
    private Long quantity;
    private Long totalQuantity;

    @Min(value = 1,message = "Giá không thể bé hơn 1")
    private Long price;

    private Long totalPrice;
    private String comment;
}
