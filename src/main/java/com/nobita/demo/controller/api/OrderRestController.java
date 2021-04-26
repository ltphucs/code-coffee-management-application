package com.nobita.demo.controller.api;

import com.nobita.demo.model.Order;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.service.OrderDetailService;
import com.nobita.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/orders")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Order order = orderService.findByID(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{idOrder}/orderDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailsByOrder(@PathVariable("idOrder") Long idOrder){
        List<OrderDetail> orderDetails=orderDetailService.findByIdOrder(idOrder);
        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Order order) {
        try {
            orderService.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Order order) {
        Order area1 = orderService.findByID(id);
        if (area1 != null) {
            orderService.update(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Order order = orderService.findByID(id);
        if (order != null) {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
