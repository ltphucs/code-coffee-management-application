package com.nobita.demo.controller.api;

import com.nobita.demo.model.BillDetail;
import com.nobita.demo.service.BillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/billDetails")
public class BillDetailsRestController {
    @Autowired
    BillDetailsService billDetailsService;

    @GetMapping(value = "/{idOrder}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@PathVariable("idOrder") Long idOrder) {
        List<BillDetail> billDetails = billDetailsService.findByIdOrder(idOrder);
        if (!billDetails.isEmpty()) {
            return new ResponseEntity<>(billDetails, HttpStatus.OK);
        }
        return new ResponseEntity<List<BillDetail>>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BillDetail billDetail) {
        try {
            billDetailsService.save(billDetail);
            return new ResponseEntity<>(billDetail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BillDetail>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
