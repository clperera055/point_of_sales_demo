package com.example.demo.service.impl;

import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.EntryDuplicationException;
import com.example.demo.exception.EntryNotFoundException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.service.CustomerService;
import com.example.demo.util.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomer(CustomerSaveDTO saveDTO) {
        Customer customer = customerMapper.saveRequestDtoToEntity(saveDTO);
        customer.setActiveState(true);

        if (!customerRepo.existsById(customer.getCustomerId())) {
            return customerRepo.save(customer).getCustomerName();
        } else
            throw new EntryDuplicationException("Customer Id already exist");

    }

    @Override
    public String updateCustomer(CustomerUpdateDTO updateDTO, int id) {
        if (customerRepo.existsById(id)) {
            /*Customer customer = customerRepo.getReferenceById(id);
            customer.setCustomerName(updateDTO.getCustomerName());
            customer.setCustomerAddress(updateDTO.getCustomerAddress());
            customer.setCustomerSalary(updateDTO.getCustomerSalary());
            customer.setContactNumbers(updateDTO.getContactNumbers());
            customer.setActiveState(updateDTO.isActiveState());
            return customerRepo.save(customer).getCustomerName();*/

            customerRepo.updateCustomer(
                    updateDTO.getCustomerName(), updateDTO.getCustomerAddress(), updateDTO.getCustomerSalary(),
                     updateDTO.getContactNumbers(), updateDTO.isActiveState()
            );

            return updateDTO.getCustomerName();
        } else
            throw new EntryNotFoundException("Invalid Customer Id....");

    }
}


