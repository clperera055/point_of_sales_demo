package com.example.demo.controller;

import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.request.OrderRequestSaveDTO;
import com.example.demo.service.OrderService;
import com.example.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order-save")
    public ResponseEntity<StandardResponse> addOrder(@RequestBody OrderRequestSaveDTO orderSaveDTO) {
        String addOrder = orderService.addOrder(orderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, " saved successfully...", addOrder),
                HttpStatus.CREATED
        );
    }
}
