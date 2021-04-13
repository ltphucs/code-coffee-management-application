package com.nobita.demo.controller.api;

import com.nobita.demo.model.Unit;
import com.nobita.demo.service.UnitService;
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
@RequestMapping("/api/units")
public class UnitRestController {
    @Autowired
    UnitService unitService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Unit> units = unitService.findAll();
        if (!units.isEmpty()) {
            return new ResponseEntity<>(units, HttpStatus.OK);
        }
        return new ResponseEntity<List<Unit>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Unit unit = unitService.findByID(id);
        if (unit != null) {
            return new ResponseEntity<>(unit, HttpStatus.OK);
        }
        return new ResponseEntity<Unit>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Unit unit, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        unitService.save(unit);
        return new ResponseEntity<>(unit,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody Unit unit, BindingResult result) {
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Unit unit1 = unitService.findByID(id);
        if (unit1 != null){
            unit1.setName(unit.getName());
            unit1.setComment(unit.getComment());
            unitService.update(unit);
            return new ResponseEntity<>(unit1,HttpStatus.OK);
        }
        return new ResponseEntity<>(unit1,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Unit unit1 = unitService.findByID(id);
        if (unit1!=null){
            unitService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
