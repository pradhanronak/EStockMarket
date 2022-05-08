package com.wellsfargo.qart.estock.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wellsfargo.qart.estock.controller.CompanyController;
import com.wellsfargo.qart.estock.controller.StockController;
import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.services.CompanyService;
import com.wellsfargo.qart.estock.services.StockService;

@WebMvcTest({ CompanyController.class, StockController.class })
@AutoConfigureMockMvc
class ExceptionTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	@MockBean
	private StockService stockService;

	@Test
	void testCompanyForExceptionUponAddingNewCompany() throws Exception {
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setCompanyName(null);

		Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? true : false);
	}

	@Test
	void testCompanyForExceptionUponAddingCompanyWithNullValue() throws Exception {
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setStockExchange(null);

		Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == 400 ? true : false);
	}

	@Test
	void testCompanyForExceptionUponFetchingCompanyInfoByNullValue() throws Exception {
		Mockito.when(companyService.getCompanyInfoById(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoByCode/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == 404 ? true : false);
	}

	@Test
	void testCompanyForExceptionUponDeletingCompanyByNullValue() throws Exception {
		Mockito.when(companyService.deleteCompany(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		assertTrue(result.getResponse().getStatus() == 200 ? true : false);
	}

//	@Test
//	void testStockForExceptionUponAddingStockWithNullValue() throws Exception {
//		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
//		stockDto.setCurrentStockPrice(null);
//
//		Mockito.when(stockService.saveStockPriceDetails(stockDto)).thenReturn(stockDto);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/addStock")
//				.content(MasterData.asJsonString(stockDto)).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		assertTrue(result.getResponse().getStatus() == 400 ? true : false);
//	}

	@Test
	void testStockForExceptionUponFetchingStockDetailsByNullValue() throws Exception {
		Mockito.when(stockService.getStockByCode(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == 400 ? true : false);
	}

}