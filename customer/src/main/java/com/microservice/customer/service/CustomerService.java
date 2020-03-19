package com.microservice.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.microservice.customer.client.CityClient;
import com.microservice.customer.controller.dto.CustomerDTO;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.service.exception.CityNotFoundException;
import com.microservice.customer.service.exception.CustomerAlreadyExistsException;

import feign.FeignException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CityClient cityClient;

	public Customer save(CustomerDTO customerDTO) {

		// Validate that the city does not exist before registering.
		Optional<Customer> customerOptional = customerRepository.findByNameIgnoreCase(customerDTO.getName());
		if (customerOptional.isPresent()) {
			throw new CustomerAlreadyExistsException();
		}

		try {
			// Validates that the customer's city is a valid city.
			cityClient.findByName(customerDTO.getCity());
		} catch (FeignException e) {
			if (e.status() == HttpStatus.NOT_FOUND.value()) {
				throw new CityNotFoundException();
			}
			throw e;
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
	 * Updates the customer's name.
	 *
	 * @param id of the customer.
	 * @param name of the customer.
	 */
	public void updateName(Long id, String name) {
		Customer savedCustomer = getById(id);
		savedCustomer.setName(name);
		customerRepository.save(savedCustomer);
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