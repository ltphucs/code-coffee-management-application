package com.nobita.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "order")
public class HomeController {

    @GetMapping
    public String showOrder(){
        return "order-table";
    }
}
