package com.wellsfargo.qart.estock.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceDetailsDTO {

	private Long Id;

	@NotNull
	@Min(value = 1, message = "CompanyCode should not be less than 1")
	@Max(value = 1000, message = "CompanyCode should not be greater than 1000")
	private Long companyCode;

	@NotNull
	@Digits(integer = 10, fraction = 2)
	private Double currentStockPrice;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Date Error")
	private LocalDate stockPriceDate;

	@NotNull
	@PastOrPresent(message = "Time Error")
	private LocalTime stockPriceTime;

}
