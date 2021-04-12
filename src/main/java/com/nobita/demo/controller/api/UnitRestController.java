package com.nobita.demo.controller.api;

import com.nobita.demo.model.Unit;
import com.nobita.demo.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "units")
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
    public ResponseEntity<?> save(@RequestBody Unit unit) {
        try {
            unitService.save(unit);
            return new ResponseEntity<>(unit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Unit>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Unit unit) {
        Unit currentUnit = unitService.findByID(id);
        if (currentUnit == null) {
            return new ResponseEntity<Unit>(HttpStatus.NOT_FOUND);
        }
        currentUnit = unit;
        try {
            unitService.update(currentUnit);
            return new ResponseEntity<>(currentUnit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Unit>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
