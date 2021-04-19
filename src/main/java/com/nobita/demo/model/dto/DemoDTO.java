package com.nobita.demo.model.dto;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class DemoDTO {
    List<Product> productList;

    List<ImportProduct> importProductList;
}
