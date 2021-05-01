package com.nobita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Long id;

    @Size(min = 3,message = "Tên thành phần không thể bé hơn 3 ký tự")
    private String name;
    private Unit unit;
    private String comment;

    private boolean deleted;

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                ", comment='" + comment + '\'' +
                '}';
    }
}
