package com.example.demo.service;

import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;

public interface CustomerService {
    String addCustomer(CustomerSaveDTO saveDTO);

    String updateCustomer(CustomerUpdateDTO updateDTO, int id);
}
