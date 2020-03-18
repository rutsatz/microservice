package com.microservice.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.customer.controller.dto.CityDTO;
import com.microservice.customer.controller.dto.CustomerDTO;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(CustomerDTO customerDTO) {

		RestTemplate client = new RestTemplate();
		ResponseEntity<CityDTO> response = client.exchange("http://localhost:8081/cities?name=" + customerDTO.getCity(),
				HttpMethod.GET, null, CityDTO.class);

		// TODO: Validar se é uma cidade válida.
		CityDTO cityDTO = response.getBody();

		Customer customer = Customer.builder()
				.name(customerDTO.getName())
				.gender(customerDTO.getGender())
				.birthDate(customerDTO.getBirthDate())
				.age(customerDTO.getAge())
				.city(customerDTO.getCity())
				.build();
		return customerRepository.save(customer);
	}

}
