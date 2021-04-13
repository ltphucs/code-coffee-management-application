package com.nobita.demo.controller.api;

import com.nobita.demo.model.ProductLine;
import com.nobita.demo.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "productLines")
public class ProductLineRestController {

    @Autowired
    ProductLineService productLineService;

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
    public ResponseEntity<?> save(@RequestBody ProductLine productLine) {
        try {
            productLineService.save(productLine);
            return new ResponseEntity<>(productLine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductLine>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductLine productLine) {
        ProductLine currentProductLine = productLineService.findByID(id);
        if (currentProductLine == null) {
            return new ResponseEntity<ProductLine>(HttpStatus.NOT_FOUND);
        }
        currentProductLine = productLine;
        try {
            productLineService.update(currentProductLine);
            return new ResponseEntity<>(currentProductLine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductLine>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
