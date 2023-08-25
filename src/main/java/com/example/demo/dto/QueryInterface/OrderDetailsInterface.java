package com.example.demo.dto.QueryInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {

    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumbers();

    Date getDate();
    double getTotal();
}
