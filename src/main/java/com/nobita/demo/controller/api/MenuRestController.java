package com.nobita.demo.controller.api;

import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.model.dto.MenuDTO;
import com.nobita.demo.service.ProductLineService;
import com.nobita.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api/menu")
public class MenuRestController {
    @Autowired
    ProductLineService productLineService;

    @Autowired
    ProductService productService;


    @GetMapping()
    public ResponseEntity<?> getProduct(){
        List<ProductLine> productLineList=productLineService.findAll();
        List<MenuDTO> menu=new ArrayList<>();
        for (ProductLine productLine:productLineList){
            MenuDTO menuDTO=new MenuDTO();
            menuDTO.setIdProductLine(productLine.getId());
            menuDTO.setNameProductLine(productLine.getName());
            menuDTO.setProductList(productService.findByProductLine(productLine.getId()));
            menu.add(menuDTO);
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
