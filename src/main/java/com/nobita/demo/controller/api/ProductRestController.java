package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.dto.DemoDTO;
import com.nobita.demo.service.ImportProductService;
import com.nobita.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @Autowired
    ImportProductService importProductService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Product> products = productService.findAll();
        List<ImportProduct> importProducts=importProductService.findAll();
        DemoDTO demoDTO=new DemoDTO();
        demoDTO.setProductList(products);
        demoDTO.setImportProductList(importProducts);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(demoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Product product = productService.findByID(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@Valid @RequestBody Product product,BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Product product1 = productService.findByID(id);
        if (product1 != null){
            product1.setName(product.getName());
            product1.setInventory(product.getInventory());
            product1.setPrice(product.getPrice());
            product1.setProductLine(product.getProductLine());
            product1.setImage(product.getImage());
            product1.setProductStatus(product.getProductStatus());
            productService.save(product1);
            return new ResponseEntity<>(product1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Product product = productService.findByID(id);
        if (product != null){
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
