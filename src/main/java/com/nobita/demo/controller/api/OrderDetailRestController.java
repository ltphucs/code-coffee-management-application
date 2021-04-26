package com.nobita.demo.controller.api;

import com.nobita.demo.model.Order;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/orderDetails")
public class OrderDetailRestController {

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<OrderDetail> orders = orderDetailService.findAll();
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        OrderDetail orderDetail = orderDetailService.findByID(id);
        if (orderDetail != null) {
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDetail orderDetail) {
        try {
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody OrderDetail orderDetail) {
        OrderDetail orderDetailCurrent = orderDetailService.findByID(id);
        if (orderDetailCurrent != null) {
            orderDetailService.update(orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        OrderDetail orderDetail = orderDetailService.findByID(id);
        if (orderDetail != null) {
            orderDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
