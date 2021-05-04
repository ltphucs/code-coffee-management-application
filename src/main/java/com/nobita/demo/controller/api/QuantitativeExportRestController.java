package com.nobita.demo.controller.api;

import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.service.QuantitativeExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/quantitativeExport")
public class QuantitativeExportRestController {
    @Autowired
    QuantitativeExportService quantitativeExportService;

    @GetMapping
    public ResponseEntity<?> list(){
        List<QuantitativeExport> quantitativeExportList=quantitativeExportService.findAll();
        if(quantitativeExportList != null ){
            return new ResponseEntity<>(quantitativeExportList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody QuantitativeExport quantitativeExport){
        try{
            quantitativeExportService.save(quantitativeExport);
            return new ResponseEntity<>(quantitativeExport,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
