package com.microservice.city.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.city.controller.dto.CityDTO;
import com.microservice.city.model.City;
import com.microservice.city.service.CityService;

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
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping(params = "name")
	public CityDTO findByName(@RequestParam String name) {
		City city = cityService.getByName(name);
		return CityDTO.builder()
				.name(city.getName())
				.state(city.getState())
				.build();
	}

	@GetMapping(params = "state")
	public List<City> findByState(@RequestParam String state) {
		return cityService.getByState(state);
	}

	@PostMapping
	public ResponseEntity<City> create(@Valid @RequestBody CityDTO cityDTO) {
		log.info("Registering a new city: {}", cityDTO);
		City savedCity = cityService.save(cityDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
	}

}
