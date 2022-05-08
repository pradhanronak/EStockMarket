package com.wellsfargo.qart.estock.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceIndexDTO {

	@NotNull
	private CompanyDetailsDTO companyDto;

	@NotNull
	private List<StockPriceDetailsDTO> stockPriceList;

	@NotNull
	@Digits(integer = 10, fraction = 2,  message = "Stock Price must have precision 10 and factional part of 2 decimals")
	private Double maxStockPrice;

	@NotNull
	@Digits(integer = 10, fraction = 2,  message = "Stock Price must have precision 10 and factional part of 2 decimals")
	private Double minStockPrice;

	@NotNull
	@Digits(integer = 10, fraction = 2,  message = "Stock Price must have precision 10 and factional part of 2 decimals")
	private Double avgStockPrice;
}
