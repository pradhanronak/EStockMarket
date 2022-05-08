package com.wellsfargo.qart.estock.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;
import com.wellsfargo.qart.estock.services.CompanyService;
import com.wellsfargo.qart.estock.services.StockService;



@WebMvcTest({ CompanyController.class, StockController.class })
@AutoConfigureMockMvc
class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	@MockBean
	private StockService stockService;

	@Test
	void testAddCompany() throws Exception {

		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getContentAsString(), MasterData.asJsonString(companyDto));

	}

	@Test
	void testDeleteCompany() throws Exception {

		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		Long companyCode = companyDto.getCompanyCode();

		Mockito.when(companyService.deleteCompany(companyCode)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getContentAsString(), MasterData.asJsonString(companyDto));

	}

	@Test
	void testGetAllCompanies() throws Exception {

		List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
		list.add(MasterData.getCompanyDetailsDTO());

		Mockito.when(companyService.getAllCompanies()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getAllCompanies")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getContentAsString(), MasterData.asJsonString(list));
	}

	@Test
	void testFindStockByCompanyCode() throws Exception {
		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
		Long companyCode = stockDto.getCompanyCode();

		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		stockList.add(stockDto);

		Mockito.when(stockService.getStockByCode(companyCode)).thenReturn(stockList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/" + companyCode)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("\"currentStockPrice\":55.76") ? true : false);
	}

	@Test
	void testStockPriceIndex() throws Exception {
		StockPriceIndexDTO stockPriceIndexDto = MasterData.getStockPriceIndexDTO();

		CompanyDetailsDTO companyDetailDTO = stockPriceIndexDto.getCompanyDto();
		Long companyCode = companyDetailDTO.getCompanyCode();

		List<StockPriceDetailsDTO> stockPDDTOList = stockPriceIndexDto.getStockPriceList();
		StockPriceDetailsDTO spDetails1 = stockPDDTOList.get(0);
		StockPriceDetailsDTO spDetails2 = stockPDDTOList.get(1);

		LocalDate startDate = spDetails1.getStockPriceDate();
		LocalDate endDate = spDetails2.getStockPriceDate();

		Mockito.when(stockService.getStockPriceIndex(companyCode, startDate, endDate))
				.thenReturn(stockPriceIndexDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/stock/getStockPriceIndex/" + companyCode + "/" + startDate + "/" + endDate)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("\"companyCode\":1001") ? true : false);
	}

	@Test
	void testCompanyForExceptionUponAddingNewCompany() throws Exception {
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setCompanyName(null);

		Mockito.when(companyService.saveCompanyDetails(companyDto)).thenReturn(companyDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
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

		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	void testCompanyForExceptionUponFetchingCompanyInfoByNullValue() throws Exception {
		Mockito.when(companyService.getCompanyInfoById(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoByCode/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(404, result.getResponse().getStatus());
	}

	@Test
	void testCompanyForExceptionUponDeletingCompanyByNullValue() throws Exception {
		Mockito.when(companyService.deleteCompany(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testStockForExceptionUponFetchingStockDetailsByNullValue() throws Exception {
		Mockito.when(stockService.getStockByCode(2L)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}

}
