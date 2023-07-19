package com.example.demo.repo;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@EnableJpaRepositories
@Transactional

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Modifying
    @Query(value = "update customer set customer_name=?1, customer_address=?2, customer_salary=?3, " +
            "contact_numbers=?4, active_state=?5 where customer_id",nativeQuery = true)
    void updateCustomer(String customerName, String customerAddress, double customerSalary, ArrayList contactNumbers, boolean activeState);
}
