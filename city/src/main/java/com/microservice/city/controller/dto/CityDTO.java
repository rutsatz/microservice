package com.microservice.city.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Data about the city")
public class CityDTO {

	/**
	 * Name of the city.
	 */
	@ApiModelProperty(value = "City's name", example = "Santa Cruz do Sul")
	@NotBlank
	private String name;

	/**
	 * State of the city.
	 */
	@ApiModelProperty(value = "State of the city", example = "RS")
	@NotBlank
	@Size(min = 2, max = 2)
	private String state;

}
