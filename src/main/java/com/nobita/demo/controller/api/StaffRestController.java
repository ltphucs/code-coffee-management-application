package com.nobita.demo.controller.api;

import com.nobita.demo.model.Staff;
import com.nobita.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "staffs")
public class StaffRestController {

    @Autowired
    StaffService staffService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Staff> staffs = staffService.findAll();
        if (!staffs.isEmpty()) {
            return new ResponseEntity<>(staffs, HttpStatus.OK);
        }
        return new ResponseEntity<List<Staff>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Staff staff = staffService.findByID(id);
        if (staff != null) {
            return new ResponseEntity<>(staff, HttpStatus.OK);
        }
        return new ResponseEntity<Staff>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Staff staff) {
        try {
            staffService.save(staff);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Staff>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Staff staff) {
        Staff currentStaff = staffService.findByID(id);
        if (currentStaff == null) {
            return new ResponseEntity<Staff>(HttpStatus.NOT_FOUND);
        }
        currentStaff = staff;
        try {
            staffService.update(currentStaff);
            return new ResponseEntity<>(currentStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Staff>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
