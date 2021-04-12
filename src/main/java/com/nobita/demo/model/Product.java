package com.nobita.demo.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Product {
    private Long id;
    private String name;
    private Long inventory;
    private Long price;
    private ProductLine productLine;
    private MultipartFile[] multiImage;
    private String image;
    private ProductStatus productStatus;
}
