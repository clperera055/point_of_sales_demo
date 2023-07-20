package com.example.demo.util.mappers;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer saveRequestDtoToEntity(CustomerSaveDTO saveDTO);

    CustomerDTO getCustomerById(Customer customer);

    List<CustomerDTO> entityListToDtoList(List<Customer> customers);
}
