package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDTO;
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

import java.util.List;
import java.util.Optional;

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
            customerRepo.updateCustomer(
                    updateDTO.getCustomerName(), updateDTO.getCustomerAddress(), updateDTO.getCustomerSalary(),
                     updateDTO.getContactNumbers(), updateDTO.isActiveState()
            );

            return updateDTO.getCustomerName();
        } else
            throw new EntryNotFoundException("Invalid Customer Id....");

    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isPresent()){
            CustomerDTO dto = customerMapper.getCustomerById(customer.get());
            return dto;
        }
        else
            throw new EntryNotFoundException("Invalid Customer Id");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        if(customers.size() >0 ){
            List<CustomerDTO> customerDTOList = customerMapper.entityListToDtoList(customers);
            return customerDTOList;
        }else
            throw new EntryNotFoundException("No customers data....");
    }
}


