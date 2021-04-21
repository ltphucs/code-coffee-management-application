package com.nobita.demo.model.dto;

import com.nobita.demo.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Long idProductLine;

    private String nameProductLine;

    private List<Product> productList;
}
