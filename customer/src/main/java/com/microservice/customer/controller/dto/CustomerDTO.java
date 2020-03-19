package com.microservice.customer.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.microservice.customer.model.Gender;

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
@ApiModel(description = "Data about the customer")
public class CustomerDTO {

	/**
	 * Customer name.
	 */
	@ApiModelProperty(value = "Customer name", example = "José da Silva", position = 2)
	@NotBlank
	private String name;

	@ApiModelProperty(value = "Customer gender.", example = "MALE", position = 3)
	@NotNull
	private Gender gender;

	/**
	 * Date of birth.
	 */
	@ApiModelProperty(value = "Date of birth. Date format: yyyy-DD-mm", example = "1990-03-05", position = 4)
	@NotNull
	@Past
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;

	/**
	 * Customer age.
	 */
	@ApiModelProperty(value = "Customer age.", example = "29", position = 5)
	@NotNull
	private Integer age;

	/**
	 * Customer city.
	 */
	@ApiModelProperty(value = "Customer city.", example = "Santa Cruz do Sul", position = 6)
	@NotBlank
	private String city;

}
