package com.nobita.demo.controller.api;

import com.nobita.demo.model.Bill;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductExport;
import com.nobita.demo.model.dto.BillDateDTO;
import com.nobita.demo.service.ProductExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/productExports")
public class ProductExportRestController {

    @Autowired
    ProductExportService productExportService;

    @PostMapping(value = "/dateExport",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody BillDateDTO billDateDTO) {
        List<ProductExport> productExportList = productExportService.findAllByDateExport(billDateDTO.getDateIn(),billDateDTO.getDateOut());
        if (!productExportList.isEmpty()) {
            return new ResponseEntity<>(productExportList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductExport productExport){
        try{
            productExportService.save(productExport);
            return new ResponseEntity<>(productExport,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
