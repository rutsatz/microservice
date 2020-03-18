package com.microservice.city.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
