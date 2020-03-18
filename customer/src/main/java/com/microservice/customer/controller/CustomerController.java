package com.microservice.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.customer.controller.dto.CustomerDTO;
import com.microservice.customer.model.Customer;
import com.microservice.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> create(@Valid @RequestBody CustomerDTO customerDTO) {
		Customer savedCustomer = customerService.save(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}
}
