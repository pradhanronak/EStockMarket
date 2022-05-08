package com.wellsfargo.qart.estock.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.entity.CompanyDetails;
import com.wellsfargo.qart.estock.entity.StockPriceDetails;

public class DtoEntityUtils {

	private DtoEntityUtils() {

	}

	public static List<CompanyDetailsDTO> convertToCompanyDetailsDtoList(List<CompanyDetails> companyDetailsList) {
		return companyDetailsList.stream().map(DtoEntityUtils::convertToCompanyDetailsDTO).collect(Collectors.toList());
	}

	public static List<StockPriceDetailsDTO> convertToStockPriceDetailsDtoList(
			List<StockPriceDetails> stockPriceDetails) {
		return stockPriceDetails.stream().map(DtoEntityUtils::convertToStockPriceDetailsDTO)
				.collect(Collectors.toList());

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
	}

	public static StockPriceDetailsDTO convertToStockPriceDetailsDTO(StockPriceDetails stockPriceDetails) {
		StockPriceDetailsDTO stockPriceDetailsDTO = new StockPriceDetailsDTO();
		BeanUtils.copyProperties(stockPriceDetails, stockPriceDetailsDTO);
		return stockPriceDetailsDTO;
	}

	public static StockPriceDetails convertToStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		StockPriceDetails stockDetails = new StockPriceDetails();
		BeanUtils.copyProperties(stockPriceDetailsDTO, stockDetails);
		return stockDetails;
	}

}
