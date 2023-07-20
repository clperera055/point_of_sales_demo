package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseCustomerDetails {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;

}
