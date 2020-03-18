package com.microservice.city.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.city.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	public Optional<City> findByName(String name);

	public Optional<List<City>> findByState(String state);

}
