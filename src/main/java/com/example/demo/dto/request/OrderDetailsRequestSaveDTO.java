package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetailsRequestSaveDTO {

    private String itemName;
    private double qty;
    private double amount;
    private int itemId;

}
