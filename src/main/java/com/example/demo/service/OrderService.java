package com.example.demo.service;

import com.example.demo.dto.paginate.PaginatedResponseOrderDetails;
import com.example.demo.dto.request.OrderRequestSaveDTO;

public interface OrderService {
    String addOrder(OrderRequestSaveDTO orderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
