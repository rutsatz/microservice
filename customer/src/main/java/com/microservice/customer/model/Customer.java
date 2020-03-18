package com.microservice.customer.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

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
