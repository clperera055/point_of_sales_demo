package com.example.demo.util.mappers;

import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer saveRequestDtoToEntity(CustomerSaveDTO saveDTO);

    Customer updateRequestDtoToEntity(CustomerUpdateDTO updateDTO);
}
