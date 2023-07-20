package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.request.CustomerSaveDTO;
import com.example.demo.dto.request.CustomerUpdateDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save-customer")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerSaveDTO saveDTO){
        String save = customerService.addCustomer(saveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, saveDTO.getCustomerName() + " saved successfully...", save),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO updateDTO,
                                                        @PathVariable (value = "id") int id){
        String update = customerService.updateCustomer(updateDTO, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, updateDTO.getCustomerName() + " updated successfully...", update),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-customer-by-id/{id}")
    public ResponseEntity<StandardResponse> getCustomerById(@PathVariable (value = "id") int id){
        CustomerDTO customerById = customerService.getCustomerById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  " Customer's details...", customerById),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List <CustomerDTO> list = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  " All Customers", list),
                HttpStatus.OK
        );
    }
}
