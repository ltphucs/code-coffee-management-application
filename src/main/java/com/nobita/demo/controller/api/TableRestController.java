package com.nobita.demo.controller.api;

import com.nobita.demo.model.Order;
import com.nobita.demo.model.Table;
import com.nobita.demo.service.OrderService;
import com.nobita.demo.service.TableService;
import com.nobita.demo.service.impl.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
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
@RequestMapping(value = "api/tables")
public class TableRestController {
    @Autowired
    TableService tableService;

    @Autowired
    OrderService orderService;

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

    @GetMapping(value = "/{idTable}/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrder(@PathVariable("idTable") Long idTable) {
        Order order = orderService.findByTable(idTable);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Table table, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        tableService.save(table);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Table table, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Table table1 = tableService.findByID(id);
        if (table1 != null) {
            table1.setName(table.getName());
            table1.setArea(table.getArea());
            table1.setTableStatus(table.getTableStatus());
            tableService.update(table1);
            return new ResponseEntity<>(table1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}/tableStatus")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id,@RequestBody Table table){
        Table tableCurrent=tableService.findByID(id);
        if( tableCurrent != null){
            tableCurrent.setTableStatus(table.getTableStatus());
            tableService.updateStatus(tableCurrent);
            return new ResponseEntity<>(tableCurrent,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Table table = tableService.findByID(id);
        if (table != null) {
            tableService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
