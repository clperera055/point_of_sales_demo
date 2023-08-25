package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseOrderDetailsDTO {
    // from customer table
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;

    //from order table
    private Date date;
    private double total;
}
