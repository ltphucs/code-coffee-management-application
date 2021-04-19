package com.nobita.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/importingredients")
public class ImportIngredientController {

    @GetMapping
    public String showIngredient(){
        return "import-ingredient";
    }
}
