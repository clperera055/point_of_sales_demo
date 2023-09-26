package com.example.demo.dto.QueryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {

    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumbers();

    Date getDate();
    double getTotal();

}
