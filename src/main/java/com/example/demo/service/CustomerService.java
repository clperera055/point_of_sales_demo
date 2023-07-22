package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;


import java.util.List;

public interface CustomerService {
    String addCustomer(CustomerSaveDTO saveDTO);

    String updateCustomer(CustomerUpdateDTO updateDTO, int id);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getCustomerByName(String name);



}
