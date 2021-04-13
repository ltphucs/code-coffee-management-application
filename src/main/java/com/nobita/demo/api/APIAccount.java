package com.nobita.demo.api;

import com.nobita.demo.model.Account;
import com.nobita.demo.service.AccountService;
import com.nobita.demo.validate.PasswordValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
public class APIAccount {

    @Autowired
    AccountService accountService;

    @Autowired
    private PasswordValidate accountValidate;

    @GetMapping
    public ResponseEntity<List<Account>> listProduct(){
       List<Account> list = accountService.findAll();
       if (list.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, BindingResult result){
        accountValidate.validate(account, result);
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        accountService.save(account);
        return new ResponseEntity<>(account,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, @PathVariable("id") long id, BindingResult result){
//     tìm ra id account
        Account account1 = accountService.findByID(id);
//      nếu có thì ktra lỗi k thì update
        if (account1 != null){
            accountValidate.validate(account,result);
            if (result.hasFieldErrors()){
                List<FieldError> fieldErrors = result.getFieldErrors();
                Map<String, String> errors = new HashMap<>();
                for (FieldError fieldError : fieldErrors) {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            account1.setUsername(account.getUsername());
            account1.setPassword(account.getPassword());
            account1.setAuthorization(account.getAuthorization());
            accountService.update(account);
            return new ResponseEntity<>(account1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
