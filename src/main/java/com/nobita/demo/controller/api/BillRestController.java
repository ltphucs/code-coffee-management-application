package com.nobita.demo.controller.api;

import com.nobita.demo.model.Bill;
import com.nobita.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/bills")
public class BillRestController {

    @Autowired
    BillService billService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Bill> bills = billService.findAll();
        if (!bills.isEmpty()) {
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Bill bill) {
        try {
            billService.save(bill);
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
