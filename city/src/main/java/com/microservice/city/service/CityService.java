package com.microservice.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.city.controller.dto.CityDTO;
import com.microservice.city.model.City;
import com.microservice.city.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City save(CityDTO cityDTO) {
		City city = City.builder()
				.name(cityDTO.getName())
				.state(cityDTO.getState())
				.build();
		return cityRepository.save(city);
	}

	public City getByName(String name) {
		return cityRepository.findByName(name).orElseThrow(() -> new RuntimeException("No city found with that name."));
	}

}
