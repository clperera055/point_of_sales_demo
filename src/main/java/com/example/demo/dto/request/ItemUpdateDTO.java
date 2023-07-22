package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemUpdateDTO {

    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;

}
