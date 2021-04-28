package com.nobita.demo.controller.api;

import com.nobita.demo.model.Bill;
import com.nobita.demo.model.BillDetail;
import com.nobita.demo.model.Order;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.service.BillDetailsService;
import com.nobita.demo.service.BillService;
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

    @Autowired
    BillService billService;

    @Autowired
    BillDetailsService billDetailsService;

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

    @GetMapping(value = "/{idOrder}/orderDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailsByOrder(@PathVariable("idOrder") Long idOrder) {
        List<OrderDetail> orderDetails = orderDetailService.findByIdOrder(idOrder);
        if (orderDetails.size() == 0){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Order order) {
        try {
            Order orderCurrent = orderService.findByTable(order.getTable().getId());
            return new ResponseEntity<>(orderCurrent, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            orderService.save(order);
            Order orderCurrent = orderService.findByTable(order.getTable().getId());
            return new ResponseEntity<>(orderCurrent, HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{idOrder}/bill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBillByIdOrder(@PathVariable("idOrder") Long idOrder) {
        Bill bill = billService.findByIdOrder(idOrder);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping(value = "/{idOrder}/billDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBillDetailsByIdOrder(@PathVariable("idOrder") Long idOrder) {
        List<BillDetail> billDetails = billDetailsService.findByIdOrder(idOrder);
        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }


    @DeleteMapping("/{idOrder}/orderDetails")
    public ResponseEntity<?> deleteByIdOrder(@PathVariable("idOrder") long idOrder) {
        List<OrderDetail> orderDetails = orderDetailService.findByIdOrder(idOrder);
        orderDetailService.deleteByIdOrder(idOrder);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/{idOrder}/product/{idProduct}/orderDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderDetailByIdOrderAndIdProduct(@PathVariable("idOrder") Long idOrder, @PathVariable("idProduct") Long idProduct) {
        try {
            OrderDetail orderDetail = orderDetailService.findByIdProductAndIdOrder(idProduct, idOrder);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{idOrder}/product/{idProduct}")
    public ResponseEntity<?> updateOrderDetailByIdOrderAndIdProduct(@PathVariable("idProduct") long idProduct,@PathVariable("idOrder") long idOrder, @RequestBody OrderDetail orderDetail) {
        OrderDetail orderDetailCurrent = orderDetailService.findByIdProductAndIdOrder(idProduct,idOrder);
        if (orderDetailCurrent != null) {
            orderDetailService.update(orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{idOrder}/product/{idProduct}")
    public ResponseEntity<?> deleteOrderDetailByIdOrderAndIdProduct(@PathVariable("idProduct") long idProduct,@PathVariable("idOrder") long idOrder) {
        OrderDetail orderDetailCurrent = orderDetailService.findByIdProductAndIdOrder(idProduct,idOrder);
        if (orderDetailCurrent != null) {
            orderDetailService.deleteByIdProductAndIdOrder(idProduct,idOrder);
            return new ResponseEntity<>(orderDetailCurrent,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
