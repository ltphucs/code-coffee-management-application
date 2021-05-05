package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.model.dto.ProductDTO;
import com.nobita.demo.service.ImportProductService;
import com.nobita.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
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
    public ResponseEntity<List<Product>> show(@RequestParam(name = "deleted", required = false) String deleted) {
        List<Product> product = deleted == null || !deleted.equals("true")
                ? productService.findAll()
                : productService.findAllIsDeleted();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> list() {
//        List<Product> products = productService.findAll();
//        if (!products.isEmpty()) {
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping(value = "/notIngredient",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listNotIngredient(){
        List<Product> products = productService.findAllNotIngredient();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
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
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result, HttpServletRequest request) throws IOException {
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
    public ResponseEntity<?> update(@PathVariable("id") Long id,@Valid @RequestBody Product product,
                                    BindingResult result,HttpServletRequest request) throws IOException {
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
            productService.update(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") long id){
//        Product product = productService.findByID(id);
//        if (product != null){
//            productService.delete(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
        productService.isDeleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<Product> restore(@PathVariable(value = "id") Long id) {
        productService.restore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
