package com.nobita.demo.controller;

import com.nobita.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public String showProductList(){
        return "product";
    }
}
