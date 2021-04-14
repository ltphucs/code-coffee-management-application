package com.nobita.demo.controller.api;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.service.IngredientService;
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
@RequestMapping(value = "api/ingredients")
public class IngredientRestController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Ingredient> ingredients = ingredientService.findAll();
        if (!ingredients.isEmpty()) {
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        }
        return new ResponseEntity<List<Ingredient>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.findByID(id);
        if (ingredient != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        }
        return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Ingredient ingredient, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        ingredientService.save(ingredient);
        return new ResponseEntity<>(ingredient,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Long id,BindingResult result, @RequestBody Ingredient ingredient) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Ingredient ingredient1 = ingredientService.findByID(id);
        if (ingredient1 != null){
            ingredient1.setName(ingredient.getName());
            ingredient1.setUnit(ingredient.getUnit());
            ingredient1.setComment(ingredient.getComment());
            ingredientService.update(ingredient);
            return new ResponseEntity<>(ingredient1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Ingredient ingredient = ingredientService.findByID(id);
        if (ingredient != null){
            ingredientService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
