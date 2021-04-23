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
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/productLines")
public class ProductLineRestController {

    @Autowired
    ProductLineService productLineService;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<ProductLine> productLines = productLineService.findAll();
        if (!productLines.isEmpty()) {
            return new ResponseEntity<>(productLines, HttpStatus.OK);
        }
        return new ResponseEntity<List<ProductLine>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ProductLine productLine = productLineService.findByID(id);
        if (productLine != null) {
            return new ResponseEntity<>(productLine, HttpStatus.OK);
        }
        return new ResponseEntity<ProductLine>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductLine productLine, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productLineService.save(productLine);
        return new ResponseEntity<>(productLine,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update( @PathVariable("id") Long id,@Valid @RequestBody ProductLine productLine,BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ProductLine productLine1 = productLineService.findByID(id);
        if (productLine1 != null){
            productLine1.setName(productLine.getName());
            productLineService.update(productLine1);
            return new ResponseEntity<>(productLine1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        ProductLine productLine = productLineService.findByID(id);
        if (productLine != null){
            productLineService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
