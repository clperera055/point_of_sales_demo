package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerSaveDTO {

    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList contactNumbers;
    private String nic;

}
