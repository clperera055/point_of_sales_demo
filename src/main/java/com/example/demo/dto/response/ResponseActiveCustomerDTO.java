package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseActiveCustomerDTO {   // end point 2

    private String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;
    private String nic;

}
