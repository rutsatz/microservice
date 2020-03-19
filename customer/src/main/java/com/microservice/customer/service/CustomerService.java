package com.microservice.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.microservice.customer.controller.dto.CustomerDTO;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.service.exception.CustomerAlreadyExistsException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(CustomerDTO customerDTO) {

//		try {
//		ResponseEntity<CityDTO> response = client.exchange("http://city/api/v1/cities?name=" + customerDTO.getCity(),
//				HttpMethod.GET, null, CityDTO.class);
//		}catch (RestClientException e) {
//			e.printStackTrace();
//		}
//		// TODO: Verificar se é uma cidade válida.
//		CityDTO cityDTO = response.getBody();
//		System.out.println("cityDTO " + cityDTO);

		// Validate that the city does not exist before registering.
		Optional<Customer> customerOptional = customerRepository.findByNameIgnoreCase(customerDTO.getName());
		if (customerOptional.isPresent()) {
			throw new CustomerAlreadyExistsException();
		}

		Customer customer = Customer.builder()
				.name(customerDTO.getName())
				.gender(customerDTO.getGender())
				.birthDate(customerDTO.getBirthDate())
				.age(customerDTO.getAge())
				.city(customerDTO.getCity())
				.build();
		return customerRepository.save(customer);
	}

	/**
	 * Search for customer in the database by name.
	 *
	 * @param name of the customer.
	 * @return the customer.
	 */
	public Customer getByName(String name) {
		return customerRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No customers found with this name.", 1));
	}

	/**
	 * Search for a customer by id.
	 *
	 * @param id of the customer.
	 * @return the customer.
	 */
	public Customer getById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("No customers found with this id.", 1));
	}
}