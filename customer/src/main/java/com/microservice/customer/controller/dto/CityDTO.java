package com.microservice.customer.controller.dto;

import lombok.Data;

@Data
public class CityDTO {

	/**
	 * Name of the city.
	 */
	private String name;

	/**
	 * State of the city.
	 */
	private String state;

}
