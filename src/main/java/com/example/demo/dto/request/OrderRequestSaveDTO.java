package com.example.demo.dto.request;

import com.example.demo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderRequestSaveDTO {

    private int customerId;
    private Date date;
    private double total;
    private List<OrderDetailsRequestSaveDTO> orderDetails;

}
