package com.microservice.customer.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All customer details.")
public class Customer {

	@ApiModelProperty(value = "Unique customer identifier", example = "123", position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	/**
	 * Customer name.
	 */
	@ApiModelProperty(value = "Customer name", example = "José da Silva", position = 2)
	private String name;

	/**
	 * Customer gender.
	 */
	@ApiModelProperty(value = "Customer gender.", example = "MALE", position = 3)
	private Gender gender;

	/**
	 * Date of birth.
	 */
	@ApiModelProperty(value = "Date of birth. Date format: yyyy-DD-mm", example = "1990-03-05", position = 4)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;

	/**
	 * Customer age.
	 */
	@ApiModelProperty(value = "Customer age.", example = "29", position = 5)
	private Integer age;

	/**
	 * Customer city.
	 */
	@ApiModelProperty(value = "Customer city.", example = "Santa Cruz do Sul", position = 6)
	private String city;

}
