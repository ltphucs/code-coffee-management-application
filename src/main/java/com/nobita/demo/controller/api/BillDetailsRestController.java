package com.nobita.demo.controller.api;

import com.nobita.demo.model.BillDetail;
import com.nobita.demo.model.ProductExport;
import com.nobita.demo.model.QuantitativeExport;
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

    @GetMapping(value = "/{idOrder}/quantitativeExports",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuantitativeExport(@PathVariable("idOrder") Long idOrder){
        List<QuantitativeExport> quantitativeExports=billDetailsService.getQuantitativeExport(idOrder);
        return new ResponseEntity<>(quantitativeExports,HttpStatus.OK);
    }

    @GetMapping(value = "/{idOrder}/productExports",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductExport(@PathVariable("idOrder") Long idOrder){
        List<ProductExport> productExports=billDetailsService.getProductExport(idOrder);
        return new ResponseEntity<>(productExports,HttpStatus.OK);
    }

    @PutMapping(value = "/{idProduct}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody BillDetail billDetail){
        try{
            billDetailsService.update(billDetail);
            return new ResponseEntity<>(billDetail,HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
