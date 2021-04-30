package com.nobita.demo.model;

import com.nobita.demo.model.en.ProductStatus;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Product {

    private Long id;

    @Size(min = 3, max = 50,message = "Tên sản phẩm phải từ trong khoảng [3-50] ký tự")
    private String name;

    private Long inventory;

    @Min(value = 0,message = "Giá sản phẩm không thể bé hơn 0")
    private Long price;
    private ProductLine productLine;

    private MultipartFile multiImage;

    @NotBlank(message = "Image be not null")
    private String image;
    private ProductStatus productStatus;

    private boolean isIngredient;
    private boolean deleted;
}
