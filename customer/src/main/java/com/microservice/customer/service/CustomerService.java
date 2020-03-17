package com.microservice.customer.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.customer.controller.dto.CityDTO;
import com.microservice.customer.controller.dto.CustomerDTO;

@Service
public class CustomerService {

	public void save(CustomerDTO customerDTO) {

		RestTemplate client = new RestTemplate();
		ResponseEntity<CityDTO> response = client.exchange("http://localhost/:8081/cities/" + customerDTO.getCity(),
				HttpMethod.GET, null, CityDTO.class);

		System.out.println(response.getBody().getState());
		
	}

}
