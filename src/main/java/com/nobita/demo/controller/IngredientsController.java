package com.nobita.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @GetMapping
    public String homeIngredient(){
        return "ingredient";
    }
}
