package com.wellsfargo.qart.estock.exceptionTests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;

public class MasterData {
	public static CompanyDetailsDTO getCompanyDetailsDTO() {
		CompanyDetailsDTO companyDetailsDTO = new CompanyDetailsDTO();

		companyDetailsDTO.setCompanyCode((long) 1001);
		companyDetailsDTO.setStockExchange("BSE");
		companyDetailsDTO.setCompanyName("IIHT Pvt Ltd, Bangalore");
		companyDetailsDTO.setCompanyCEO("Praveen Kumar");
		companyDetailsDTO.setTurnover(65432.87);
		companyDetailsDTO.setBoardOfDirectors("AAA, BBB, CCC");
		companyDetailsDTO.setCompanyProfile("Base location is Bangalore, India");

		return companyDetailsDTO;
	}

	public static StockPriceDetailsDTO getStockPriceDetailsDTO() {
		StockPriceDetailsDTO stockPriceDetailsDTO = new StockPriceDetailsDTO();

		stockPriceDetailsDTO.setId((long) 1001);
		stockPriceDetailsDTO.setCompanyCode((long) 2001);
		stockPriceDetailsDTO.setCurrentStockPrice(55.76);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO.setStockPriceDate(LocalDate.parse("08/07/2020", dateFormat));
		stockPriceDetailsDTO.setStockPriceTime(LocalTime.parse("10:30:00"));

		return stockPriceDetailsDTO;
	}

	public static StockPriceIndexDTO getStockPriceIndexDTO() {
		StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();

		CompanyDetailsDTO companyDetailsDTO = getCompanyDetailsDTO();

		List<StockPriceDetailsDTO> stockPriceDetailsList = new ArrayList<StockPriceDetailsDTO>();

		StockPriceDetailsDTO stockPriceDetailsDTO1 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO1.setId((long) 1001);
		stockPriceDetailsDTO1.setCompanyCode((long) 2001);
		stockPriceDetailsDTO1.setCurrentStockPrice(55.76);
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO1.setStockPriceDate(LocalDate.parse("08/07/2020", dateFormat1));
		stockPriceDetailsDTO1.setStockPriceTime(LocalTime.parse("10:30:00"));

		StockPriceDetailsDTO stockPriceDetailsDTO2 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO2.setId((long) 1002);
		stockPriceDetailsDTO2.setCompanyCode((long) 2002);
		stockPriceDetailsDTO2.setCurrentStockPrice(75.76);
		DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO2.setStockPriceDate(LocalDate.parse("08/10/2020", dateFormat2));
		stockPriceDetailsDTO2.setStockPriceTime(LocalTime.parse("09:30:00"));

		stockPriceDetailsList.add(stockPriceDetailsDTO1);
		stockPriceDetailsList.add(stockPriceDetailsDTO2);

		stockPriceIndexDTO.setCompanyDto(companyDetailsDTO);
		stockPriceIndexDTO.setStockPriceList(stockPriceDetailsList);
		stockPriceIndexDTO.setMaxStockPrice(85.55);
		stockPriceIndexDTO.setAvgStockPrice(47.15);
		stockPriceIndexDTO.setMinStockPrice(20.25);

		return stockPriceIndexDTO;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}