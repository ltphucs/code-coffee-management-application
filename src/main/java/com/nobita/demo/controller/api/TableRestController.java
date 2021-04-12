package com.nobita.demo.controller.api;

import com.nobita.demo.model.Table;
import com.nobita.demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "tables")
public class TableRestController {
    @Autowired
    TableService tableService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Table> tables = tableService.findAll();
        if (!tables.isEmpty()) {
            return new ResponseEntity<>(tables, HttpStatus.OK);
        }
        return new ResponseEntity<List<Table>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Table table = tableService.findByID(id);
        if (table != null) {
            return new ResponseEntity<>(table, HttpStatus.OK);
        }
        return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Table table) {
        try {
            tableService.save(table);
            return new ResponseEntity<>(table, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Table>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Table table) {
        Table currentTable = tableService.findByID(id);
        if (currentTable == null) {
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
        currentTable = table;
        try {
            tableService.update(currentTable);
            return new ResponseEntity<>(currentTable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Table>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
