package com.nobita.demo.controller.api;

import com.nobita.demo.model.Staff;
import com.nobita.demo.service.StaffService;
import com.nobita.demo.validate.StaffPasswordValidate;
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
@RequestMapping(value = "api/staffs")
public class StaffRestController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffPasswordValidate validate;
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
    public ResponseEntity<?> save(@Valid @RequestBody Staff staff, BindingResult result) {
        validate.validate(staff,result);
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        staffService.save(staff);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody Staff staff, BindingResult result) {
        validate.validate(staff,result);
        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Staff staff1 = staffService.findByID(id);
        if (staff1 != null){
            staff1.setDateJoin(staff.getDateJoin());
            staff1.setFullName(staff.getFullName());
            staff1.setGender(staff.getGender());
            staff1.setPosition(staff.getPosition());
            staff1.setDateOfBirth(staff.getDateOfBirth());
            staff1.setAddress(staff.getAddress());
            staff1.setPhone(staff.getPhone());
            staff1.setUsername(staff.getUsername());
            staff1.setPassword(staff.getPassword());

            staffService.update(staff);
            return new ResponseEntity<>(staff1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Staff staff = staffService.findByID(id);
        if (staff != null){
            staffService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
