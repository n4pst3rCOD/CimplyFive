package com.cimply.assignment.repository;

import com.cimply.assignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmailID(String emailID);

    Customer findByMobileNumber(String mobileNumber);

    Long countByEmailID(String emailID);

    Long countByMobileNumber(String mobileNumber);
}
