package com.microservice.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.customer.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public Optional<Customer> findByNameIgnoreCase(String name);

}
