package com.microservice.city.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.microservice.city.controller.dto.CityDTO;
import com.microservice.city.model.City;
import com.microservice.city.repository.CityRepository;
import com.microservice.city.service.exception.CityAlreadyExistsException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City save(CityDTO cityDTO) {

		// Validate that the city does not exist before registering.
		Optional<City> cityOptional = cityRepository.findByNameIgnoreCase(cityDTO.getName());
		if (cityOptional.isPresent()) {
			throw new CityAlreadyExistsException();
		}

		City city = City.builder()
				.name(cityDTO.getName())
				.state(cityDTO.getState())
				.build();

		return cityRepository.save(city);
	}

	/**
	 * Search for a city in the database by name.
	 *
	 * @param name of the city.
	 * @return the city.
	 */
	public City getByName(String name) {
		return cityRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No cities found with that name.", 1));
	}

	/**
	 * Search for a list of cities that are registered for the state.
	 *
	 * @param state to look for
	 * @return List of cities in the state
	 */
	public List<City> getByState(String state) {
		return cityRepository.findByStateIgnoreCase(state)
				.orElseThrow(() -> new EmptyResultDataAccessException("No city registered for this state.", 1));
	}

}
