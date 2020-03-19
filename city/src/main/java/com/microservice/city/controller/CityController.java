package com.microservice.city.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.city.controller.dto.CityDTO;
import com.microservice.city.exceptionhandler.CityExceptionHandler.Error;
import com.microservice.city.model.City;
import com.microservice.city.service.CityService;
import com.microservice.city.service.exception.CityAlreadyExistsException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for meeting requests related to cities.
 *
 * @author rafael.rutsatz
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/cities")
@Api(value = "City", tags = "City")
public class CityController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Search city by name.", response = CityDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
	public CityDTO findByName(
			@ApiParam(value = "City's name", required = true) @RequestParam(required = true) String name) {
		City city = cityService.getByName(name);
		return CityDTO.builder()
				.name(city.getName())
				.state(city.getState())
				.build();
	}

	@ApiOperation(value = "Search the cities of the state.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(params = "state", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<City> findByState(
			@ApiParam(value = "State you want to look for", required = true) @RequestParam(required = true) String state) {
		return cityService.getByState(state);
	}

	@ApiOperation(value = "Register a new city.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> create(@ApiParam(value = "City data") @Valid @RequestBody CityDTO cityDTO) {
		log.info("Registering a new city: {}", cityDTO);
		City savedCity = cityService.save(cityDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
	}

	/**
	 * Handle the exception when a registered city already exists.
	 *
	 * @param ex Exception that was thrown.
	 * @return Response to be sent to the user.
	 */
	@ExceptionHandler({ CityAlreadyExistsException.class })
	public ResponseEntity<Object> handleCityAlreadyExistsException(CityAlreadyExistsException ex) {
		String userMessage = "There is already a city registered with this name.";
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}

}
