package com.wellsfargo.qart.estock.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.entity.CompanyDetails;
import com.wellsfargo.qart.estock.entity.StockPriceDetails;


public class DtoEntityUtils {
	public static List<CompanyDetailsDTO> convertToCompanyDetailsDtoList(List<CompanyDetails> companyDetailsList) {
		List<CompanyDetailsDTO> companyDetailsDTOList = new ArrayList<CompanyDetailsDTO>();
		companyDetailsDTOList = companyDetailsList.stream()
				.map(companyDetails -> convertToCompanyDetailsDTO(companyDetails))
				.collect(Collectors.toList());
		return companyDetailsDTOList;
	}

	public static List<StockPriceDetailsDTO> convertToStockPriceDetailsDtoList(List<StockPriceDetails> stockPriceDetails) {
		List<StockPriceDetailsDTO> stockPriceDetailsDTOlist = new ArrayList<StockPriceDetailsDTO>();
		stockPriceDetailsDTOlist = stockPriceDetails.stream()
				.map(stockDetails -> convertToStockPriceDetailsDTO(stockDetails))
				.collect(Collectors.toList());
		return stockPriceDetailsDTOlist;
	}

	public static CompanyDetailsDTO convertToCompanyDetailsDTO(CompanyDetails companyDetails) {
		CompanyDetailsDTO companyDetailsDTO = new CompanyDetailsDTO();
		BeanUtils.copyProperties(companyDetails, companyDetailsDTO);
		return companyDetailsDTO;
	}

	public static CompanyDetails convertToCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyDetailsDTO, companyDetails);
		return companyDetails;
	};

	public static StockPriceDetailsDTO convertToStockPriceDetailsDTO(StockPriceDetails stockPriceDetails) {
		StockPriceDetailsDTO stockPriceDetailsDTO = new StockPriceDetailsDTO();
		BeanUtils.copyProperties(stockPriceDetails, stockPriceDetailsDTO);
		return stockPriceDetailsDTO;
	};

	public static StockPriceDetails convertToStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		StockPriceDetails stockDetails = new StockPriceDetails();
		BeanUtils.copyProperties(stockPriceDetailsDTO, stockDetails);
		return stockDetails;
	};

}
