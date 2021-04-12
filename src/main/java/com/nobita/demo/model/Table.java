package com.nobita.demo.model;

import lombok.Data;

@Data
public class Table {
    private Long id;
    private String name;
    private Area area;
    private TableStatus tableStatus;
}
