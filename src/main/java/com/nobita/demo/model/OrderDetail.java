package com.nobita.demo.model;

import lombok.Data;

@Data
public class OrderDetail {
    private Order order;
    private Product product;
    private Long quantity;
    private Long priceEach;
    private Long totalPrice;
    private DetailStatus detailStatus;
}
