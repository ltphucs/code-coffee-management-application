package com.nobita.demo.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Table {
    private Long id;

    @Size(min = 3,message = "Độ dài tên bàn ít nhất 3 ký tự")
    private String name;
    private Area area;
    private TableStatus tableStatus;
}
