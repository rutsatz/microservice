package com.microservice.customer.controller.dto;

import java.time.LocalDate;

import com.microservice.customer.model.Gender;

import lombok.Data;

@Data
public class CustomerDTO {

	/**
	 * Customer name.
	 */
	private String name;

	private Gender gender;

	/**
	 * Date of birth.
	 */
	private LocalDate birthDate;

	/**
	 * Customer age.
	 */
	private Integer age;

	/**
	 * Customer city.
	 */
	private String city;

}
