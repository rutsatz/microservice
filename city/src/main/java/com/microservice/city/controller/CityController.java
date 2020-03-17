package com.microservice.city.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.city.controller.dto.CityDTO;


@RestController
@RequestMapping("/cities")
public class CityController {

	@PostMapping
	public ResponseEntity<CityDTO> create(@Valid @RequestBody CityDTO cityDTO) {
		System.out.println(cityDTO);
		return null;
	}
	
}
