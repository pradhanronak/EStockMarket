package com.wellsfargo.qart.estock.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailsDTO {
	
	private Long companyCode;

	@NotNull(message = "stockExchange cannot be null")
	@Size(min = 3, message = "stockExchange must be at least 3 characters.")
	@Size(max = 100, message = "stockExchange must be less than/equal to 100 characters")
	private String stockExchange;

	@NotNull(message = "companyName cannot be null")
	@Size(min = 3, message = "companyName must be at least 3 characters.")
	@Size(max = 100, message = "companyName must be less than/equal to 100 characters")
	private String companyName;

	@NotNull(message = "companyCEO cannot be null")
	@Size(min = 5, message = "companyCEO must be at least 5 characters.")
	@Size(max = 100, message = "companyCEO must be less than/equal to 100 characters")
	private String companyCEO;

	@NotNull
	@Column(precision = 10, scale = 2)
	private Double turnover;

	@NotNull(message = "boardOfDirectors cannot be null")
	@Size(min = 5, message = "boardOfDirectors must be at least 5 characters.")
	@Size(max = 200, message = "boardOfDirectors must be less than/equal to 200 characters")
	private String boardOfDirectors;

	@NotNull(message = "companyProfile cannot be null")
	@Size(min = 5, message = "companyProfile must be at least 5 characters.")
	@Size(max = 255, message = "companyProfile must be less than/equal to 255 characters")
	private String companyProfile;

}
