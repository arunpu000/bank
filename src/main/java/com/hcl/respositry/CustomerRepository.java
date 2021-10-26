package com.hcl.respositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcl.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
Customer findByPanNumber(String panNumber);

@Query("from Customer where phoneNumber=:phoneNumber")
Customer findByPhoneNumber(String phoneNumber);
}
