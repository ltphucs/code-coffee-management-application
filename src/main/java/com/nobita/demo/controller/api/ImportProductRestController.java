package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.service.ImportProductService;
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
@RequestMapping("/api/importProducts")
public class ImportProductRestController {
    @Autowired
    ImportProductService importProductService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<ImportProduct> importProducts = importProductService.findAll();
        if (!importProducts.isEmpty()) {
            return new ResponseEntity<>(importProducts, HttpStatus.OK);
        }
        return new ResponseEntity<List<ImportProduct>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get( @PathVariable("id") long id) {
        ImportProduct importProduct = importProductService.findByID(id);
        if (importProduct != null) {
            return new ResponseEntity<>(importProduct, HttpStatus.OK);
        }
        return new ResponseEntity<ImportProduct>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ImportProduct importProduct, BindingResult result) {
        if (result.hasFieldErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        importProductService.save(importProduct);
        return new ResponseEntity<>(importProduct,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update( @PathVariable("id") Long id,@Valid @RequestBody ImportProduct importProduct,BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ImportProduct importProduct1 = importProductService.findByID(id);
        if (importProduct1 != null){
            importProduct1.setDateJoin(importProduct.getDateJoin());
            importProduct1.setProduct(importProduct.getProduct());
            importProduct1.setQuantity(importProduct.getQuantity());
            importProduct1.setPrice(importProduct.getPrice());
            importProduct1.setTotalPrice(importProduct.getTotalPrice());
            importProduct1.setComment(importProduct.getComment());
            importProductService.save(importProduct1);
            return new ResponseEntity<>(importProduct1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        ImportProduct importProduct = importProductService.findByID(id);
        if (importProduct != null){
            importProductService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
