package com.nobita.demo.model.dto;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    Product product;

    List<ImportProduct> importProductList;
}
