package com.microservice.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.customer.controller.dto.CityDTO;

@FeignClient("city")
public interface CityClient {

	@GetMapping(value = "/api/v1/cities", params = "name")
	public CityDTO findByName(@RequestParam(required = true) String name);

}
