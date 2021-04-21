package com.nobita.demo.controller.api;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.service.ImportIngredientService;
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
@RequestMapping(value = "api/importIngredients")
public class ImportIngredientRestController {

    @Autowired
    private ImportIngredientService importIngredientService;

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
    public ResponseEntity<?> save(@Valid @RequestBody ImportIngredient importIngredient, BindingResult result) {
        if (result.hasFieldErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        importIngredientService.save(importIngredient);
        return new ResponseEntity<>(importIngredient,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update( @PathVariable("id") long id,@Valid @RequestBody ImportIngredient importIngredient,BindingResult result) {
        if (result.hasFieldErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ImportIngredient importIngredient1 = importIngredientService.findByID(id);
        if (importIngredient1 != null){
            importIngredientService.update(importIngredient);
            return new ResponseEntity<>(importIngredient,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        ImportIngredient importIngredient1 = importIngredientService.findByID(id);
        if (importIngredient1 != null){
            importIngredientService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
