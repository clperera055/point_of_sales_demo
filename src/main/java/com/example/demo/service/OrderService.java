package com.example.demo.service;

import com.example.demo.dto.request.OrderRequestSaveDTO;

public interface OrderService {
    String addOrder(OrderRequestSaveDTO orderSaveDTO);
}
