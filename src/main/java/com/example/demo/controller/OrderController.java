package com.example.demo.controller;

import com.example.demo.dto.paginate.PaginatedResponseOrderDetails;
import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.request.OrderRequestSaveDTO;
import com.example.demo.service.OrderService;
import com.example.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-order-details/{state},{page}")
    public ResponseEntity<StandardResponse> getAllOrderDetails(@PathVariable (value = "state") String state,
                                                               @PathVariable (value = "page") int page){

        PaginatedResponseOrderDetails paginatedResponseOrderDetails = null;
        int size = 0;
        if(state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")){
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            paginatedResponseOrderDetails = orderService.getAllOrderDetails(status, page, size);
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", paginatedResponseOrderDetails),
                HttpStatus.OK
        );
    }
}
