package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.dto.response.ResponseActiveCustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.EntryDuplicationException;
import com.example.demo.exception.EntryNotFoundException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.service.CustomerService;
import com.example.demo.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

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
        if (customer.isPresent()) {
            CustomerDTO dto = customerMapper.getCustomerById(customer.get());
            return dto;
        } else
            throw new EntryNotFoundException("Invalid Customer Id");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        if (customers.size() > 0) {
            List<CustomerDTO> customerDTOList = customerMapper.entityListToDtoList(customers);
            return customerDTOList;
        } else
            throw new EntryNotFoundException("No customers data....");
    }

    @Override
    public List<CustomerDTO> getCustomerByName(String name) {
        List<Customer> list = customerRepo.findAllByCustomerNameAndActiveStateEquals(name, true);
        if (list.size() > 0) {
            List<CustomerDTO> dtoList = customerMapper.getActiveCustomersByName(list);
            return dtoList;
        } else
            throw new EntryNotFoundException("Invalid Customer List.....");
    }

    @Override
    public List<ResponseActiveCustomerDTO> getAllActiveCustomers() {
        List<Customer> customerList = customerRepo.findAllByActiveStateEquals(true);
        if (customerList.size() > 0) {
            List<ResponseActiveCustomerDTO> activeCustomerDTOList = customerMapper.responseActiveCustomerList(customerList);
            return activeCustomerDTOList;
        } else
            throw new EntryNotFoundException("Invalid Request.......");
    }

    @Override
    public List<CustomerDTO> getAllCustomersByState(boolean status) {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(status);
        if(customers.size() > 0){
            List<CustomerDTO> customerDTOS = customerMapper.getAllCustomersByActiveStatus(customers);
            return customerDTOS;
        }else
            throw new EntryNotFoundException("Invalid Entry...");

    }

    @Override
    public int getAllCustomersByCount(boolean val) {
        int count = customerRepo.countAllByActiveStateEquals(val);
        return count;
    }

    @Override
    public int getAllCustomerCount() {
        int count = (int) customerRepo.count();
        return count;
    }


}


