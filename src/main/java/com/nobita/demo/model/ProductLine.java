package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProductLine {
    private Long id;

    @Size(min = 3, max = 50, message = "Tên dòng sản phẩm phải từ trong khoảng [3-50] ký tự")
    private String name;

    private boolean isDeleted;
}
