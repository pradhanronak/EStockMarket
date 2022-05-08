package com.wellsfargo.qart.estock.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;
import com.wellsfargo.qart.estock.entity.CompanyDetails;
import com.wellsfargo.qart.estock.entity.StockPriceDetails;
import com.wellsfargo.qart.estock.exceptions.InvalidCompanyException;
import com.wellsfargo.qart.estock.exceptions.InvalidStockException;



@SpringBootTest
class UtilityTest {

	DateTimeFormatter dateformatter = new DateTimeFormatterBuilder().parseStrict().appendPattern("uuuuMMdd")
			.toFormatter().withResolverStyle(ResolverStyle.STRICT);

	String date = "20220503";
	String time = "11:30:25";
	LocalDate ldate = LocalDate.parse(date, dateformatter);
	LocalTime ltime = LocalTime.parse(time);

	@Test
	void CompanyTestConstructor() {
		CompanyDetails company = new CompanyDetails(1L, "NSE", "Wells Fargo", "John", 10000.00,
				"John", "Banking");
		assertEquals(1L, company.getCompanyCode());
		assertEquals("NSE", company.getStockExchange());
		assertEquals("Wells Fargo", company.getCompanyName());
		assertEquals("John", company.getCompanyCEO());
		assertEquals(10000.00, company.getTurnover());
		assertEquals("John", company.getBoardOfDirectors());
		assertEquals("Banking", company.getCompanyProfile());
	}

	@Test
	void testStockException() {
		InvalidStockException se = new InvalidStockException("Invalid Stock Price Details");
		assertEquals("Invalid Stock Price Details", se.getMessage());
	}

	@Test
	void testCompanyException() {
		InvalidCompanyException ce = new InvalidCompanyException("Invalid company Details");
		assertEquals("Invalid company Details", ce.getMessage());
	}

	@Test
	void StockTestConstructor() {

		StockPriceDetails stock = new StockPriceDetails(1L, 1L, 1000.00, ldate, ltime);

		assertEquals(1L, stock.getId());
		assertEquals(1L, stock.getCompanyCode());
		assertEquals(1000.00, stock.getCurrentStockPrice());
		assertEquals(ldate, stock.getStockPriceDate());
		assertEquals(ltime, stock.getStockPriceTime());
	}

	@Test
	void CompanyTestSetter() {
		CompanyDetails company = new CompanyDetails();
		company.setCompanyCode(2L);
		company.setStockExchange("Bombay Stock Exchange");
		company.setCompanyName("LIC");
		company.setCompanyCEO("William");
		company.setTurnover(5000.00);
		company.setBoardOfDirectors("William");
		company.setCompanyProfile("Insurance");

		assertEquals(2L, company.getCompanyCode());
		assertEquals("Bombay Stock Exchange", company.getStockExchange());
		assertEquals("LIC", company.getCompanyName());
		assertEquals("William", company.getCompanyCEO());
		assertEquals(5000.00, company.getTurnover());
		assertEquals("William", company.getBoardOfDirectors());
		assertEquals("Insurance", company.getCompanyProfile());
	}

	@Test
	void StockTestSetter() {
		StockPriceDetails stock = new StockPriceDetails();
		stock.setId(1L);
		stock.setCompanyCode(1L);
		stock.setCurrentStockPrice(1000.00);
		stock.setStockPriceDate(ldate);
		stock.setStockPriceTime(ltime);

		assertEquals(1L, stock.getId());
		assertEquals(1L, stock.getCompanyCode());
		assertEquals(1000.00, stock.getCurrentStockPrice());
		assertEquals(ldate, stock.getStockPriceDate());
		assertEquals(ltime, stock.getStockPriceTime());
	}

	@Test
	void StockDTOTestSetter() {
		StockPriceDetailsDTO stock = new StockPriceDetailsDTO();
		stock.setId(1L);
		stock.setCompanyCode(1L);
		stock.setCurrentStockPrice(1000.00);
		stock.setStockPriceDate(ldate);
		stock.setStockPriceTime(ltime);

		assertEquals(1L, stock.getId());
		assertEquals(1L, stock.getCompanyCode());
		assertEquals(1000.00, stock.getCurrentStockPrice());
		assertEquals(ldate, stock.getStockPriceDate());
		assertEquals(ltime, stock.getStockPriceTime());
	}

	@Test
	void StockPriceIndexDTOTestSetter() {
		StockPriceIndexDTO spiDTO = new StockPriceIndexDTO();
		spiDTO.setAvgStockPrice(150.00);
		spiDTO.setMaxStockPrice(200.00);
		spiDTO.setMinStockPrice(100.00);

		assertEquals(150.00, spiDTO.getAvgStockPrice());
		assertEquals(200.00, spiDTO.getMaxStockPrice());
		assertEquals(100.00, spiDTO.getMinStockPrice());
	}

	@Test
	void CompanyDTOTestSetter() throws Exception {
		CompanyDetailsDTO company = new CompanyDetailsDTO();
		company.setCompanyCode(2L);
		company.setStockExchange("Bombay Stock Exchange");
		company.setCompanyName("LIC");
		company.setCompanyCEO("William");
		company.setTurnover(5000.00);
		company.setBoardOfDirectors("William");
		company.setCompanyProfile("Insurance");

		assertEquals(2L, company.getCompanyCode());
		assertEquals("Bombay Stock Exchange", company.getStockExchange());
		assertEquals("LIC", company.getCompanyName());
		assertEquals("William", company.getCompanyCEO());
		assertEquals(5000.00, company.getTurnover());
		assertEquals("William", company.getBoardOfDirectors());
		assertEquals("Insurance", company.getCompanyProfile());
	}
}
