package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "importProducts")
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
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ImportProduct importProduct = importProductService.findByID(id);
        if (importProduct != null) {
            return new ResponseEntity<>(importProduct, HttpStatus.OK);
        }
        return new ResponseEntity<ImportProduct>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ImportProduct importProduct) {
        try {
            importProductService.save(importProduct);
            return new ResponseEntity<>(importProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ImportProduct>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ImportProduct importProduct) {
        ImportProduct currentImportProduct = importProductService.findByID(id);
        if (currentImportProduct == null) {
            return new ResponseEntity<ImportProduct>(HttpStatus.NOT_FOUND);
        }
        currentImportProduct = importProduct;
        try {
            importProductService.update(currentImportProduct);
            return new ResponseEntity<>(currentImportProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ImportProduct>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
