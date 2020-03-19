package com.microservice.customer.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.customer.controller.dto.CustomerDTO;
import com.microservice.customer.exceptionhandler.CustomerExceptionHandler.Error;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.service.CustomerService;
import com.microservice.customer.service.exception.CustomerAlreadyExistsException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for meeting customer-related requests.
 *
 * @author rafael.rutsatz
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@Api(value = "Customer", tags = "Customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation(value = "Search customer by id.", response = Customer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findById(@ApiParam(value = "Customer id", required = true) @PathVariable Long id) {
		return customerService.getById(id);
	}

	@ApiOperation(value = "Search customer by name.", response = Customer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findByName(
			@ApiParam(value = "Customer name", required = true) @RequestParam(required = true) String name) {
		return customerService.getByName(name);
	}

	@ApiOperation(value = "Register a new customer.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> create(
			@ApiParam(value = "New customer information") @Valid @RequestBody CustomerDTO customerDTO) {
		log.info("Registering a new customer: {}", customerDTO);
		Customer savedCustomer = customerService.save(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}

	@ApiOperation(value = "Delete an existing customer by their id.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "Customer id", required = true) @PathVariable Long id) {
		log.info("Deleting customer id {}", id);
		customerRepository.deleteById(id);
	}

	@ApiOperation(value = "Update customer name.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PutMapping(value = "/{id}/name", consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@ApiParam(value = "Customer id.", example = "123", required = true) @PathVariable Long id,
			@ApiParam(value = "Customer name to be updated.", example = "José da Silve", required = true) @RequestBody String name) {
		log.info("Update customer id {} to name {}", id, name);
		customerService.updateName(id, name);
	}

	/**
	 * Handle the exception when trying to register a customer that already exists.
	 *
	 * @param ex Exception that was thrown.
	 * @return Response to be sent to the user.
	 */
	@ExceptionHandler({ CustomerAlreadyExistsException.class })
	public ResponseEntity<Object> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
		String userMessage = "There is already a registered customer with this name.";
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}
}
