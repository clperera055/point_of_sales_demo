package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.dto.response.ResponseActiveCustomerDTO;
import com.example.demo.dto.response.ResponseCustomerDetails;

import java.util.List;

public interface CustomerService {
    String addCustomer(CustomerSaveDTO saveDTO);

    String updateCustomer(CustomerUpdateDTO updateDTO, int id);

    CustomerDTO getCustomerById(int id);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getCustomerByName(String name);

    List<ResponseActiveCustomerDTO> getAllActiveCustomers();

    List<CustomerDTO> getAllCustomersByState(boolean status);

    int getAllCustomersByCount(boolean val);

    int getAllCustomerCount();
}
