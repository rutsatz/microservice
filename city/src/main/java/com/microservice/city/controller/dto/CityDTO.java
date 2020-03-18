package com.microservice.city.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank
	private String name;

	/**
	 * State of the city.
	 */
	@NotBlank
	@Size(min = 2, max = 2)
	private String state;

}
