package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.service.ImportIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "importIngredients")
public class ImportIngredientRestController {
    @Autowired
    ImportIngredientService importIngredientService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<ImportIngredient> importIngredients = importIngredientService.findAll();
        if (!importIngredients.isEmpty()) {
            return new ResponseEntity<>(importIngredients, HttpStatus.OK);
        }
        return new ResponseEntity<List<ImportIngredient>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ImportIngredient importIngredient = importIngredientService.findByID(id);
        if (importIngredient != null) {
            return new ResponseEntity<>(importIngredient, HttpStatus.OK);
        }
        return new ResponseEntity<ImportIngredient>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ImportIngredient importIngredient) {
        try {
            importIngredientService.save(importIngredient);
            return new ResponseEntity<>(importIngredient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ImportIngredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ImportIngredient importIngredient) {
        ImportIngredient currentImportIngredient = importIngredientService.findByID(id);
        if (currentImportIngredient == null) {
            return new ResponseEntity<ImportIngredient>(HttpStatus.NOT_FOUND);
        }
        currentImportIngredient = importIngredient;
        try {
            importIngredientService.update(currentImportIngredient);
            return new ResponseEntity<>(currentImportIngredient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ImportIngredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
