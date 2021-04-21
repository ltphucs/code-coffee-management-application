package com.nobita.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/importProducts")
public class ImportProductController {
    @GetMapping
    public String show(){
        return "import-product";
    }
}
