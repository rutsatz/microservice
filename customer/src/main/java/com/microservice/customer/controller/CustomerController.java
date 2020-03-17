package com.microservice.customer.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.customer.controller.dto.CustomerDTO;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@PostMapping
	public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customerDTO) {
		System.out.println(customerDTO);
		return null;
	}
}
