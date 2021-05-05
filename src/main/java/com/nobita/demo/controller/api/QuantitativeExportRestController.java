package com.nobita.demo.controller.api;

import com.nobita.demo.model.Bill;
import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.model.dto.BillDateDTO;
import com.nobita.demo.service.QuantitativeExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/quantitativeExports")
public class QuantitativeExportRestController {
    @Autowired
    QuantitativeExportService quantitativeExportService;

    @PostMapping(value = "/dateExport",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody BillDateDTO billDateDTO) {
        List<QuantitativeExport> quantitativeExports = quantitativeExportService.findAllByDateExport(billDateDTO.getDateIn(),billDateDTO.getDateOut()    );
        if (!quantitativeExports.isEmpty()) {
            return new ResponseEntity<>(quantitativeExports, HttpStatus.OK);
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
