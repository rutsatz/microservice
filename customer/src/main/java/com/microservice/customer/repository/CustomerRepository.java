package com.microservice.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.customer.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
