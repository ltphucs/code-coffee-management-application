package com.nobita.demo.controller.api;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "ingredients")
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
    public ResponseEntity<?> save(@RequestBody Ingredient ingredient) {
        try {
            ingredientService.save(ingredient);
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Ingredient ingredient) {
        Ingredient currentIngredient = ingredientService.findByID(id);
        if (currentIngredient == null) {
            return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
        }
        currentIngredient = ingredient;
        try {
            ingredientService.update(currentIngredient);
            return new ResponseEntity<>(currentIngredient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
