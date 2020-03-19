package com.microservice.city.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
@ApiModel(description = "All details about the city")
public class City {

	@ApiModelProperty(value = "Unique city identifier", example = "123")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ApiModelProperty(value = "City's name", example = "Santa Cruz do Sul")
	private String name;

	@ApiModelProperty(value = "State of the city", example = "RS")
	private String state;

	/**
	 * Ensures that the state will always be saved in capital letters.
	 */
	@PrePersist
	private void prePersist() {
		this.state = this.state.toUpperCase();
	}

}
