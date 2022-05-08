package com.wellsfargo.qart.estock.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;
import com.wellsfargo.qart.estock.entity.CompanyDetails;
import com.wellsfargo.qart.estock.entity.StockPriceDetails;
import com.wellsfargo.qart.estock.exceptions.CompanyNotFoundException;
import com.wellsfargo.qart.estock.exceptions.StockNotFoundException;
import com.wellsfargo.qart.estock.repository.CompanyDetailsRepository;
import com.wellsfargo.qart.estock.repository.StockPriceRepository;
import com.wellsfargo.qart.estock.services.StockService;
import com.wellsfargo.qart.estock.utils.DtoEntityUtils;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Autowired
	CompanyDetailsRepository companyDetailsRepository;

	@Override
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		StockPriceDetails stockPriceDetails = stockPriceRepository
				.save(DtoEntityUtils.convertToStockPriceDetails(stockPriceDetailsDTO));
		return DtoEntityUtils.convertToStockPriceDetailsDTO(stockPriceDetails);
	}

	@Override
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode) {
		List<StockPriceDetails> stockPriceDetailsList = stockPriceRepository.findStockByCompanyCode(companyCode);
		if (CollectionUtils.isEmpty(stockPriceDetailsList))
			throw new StockNotFoundException("Stock with company code [" + companyCode + "] not found.");
		else
			return DtoEntityUtils.convertToStockPriceDetailsDtoList(stockPriceDetailsList);
	}

	@Override
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {

		StockPriceIndexDTO stockPriceIndexDto = new StockPriceIndexDTO();

		CompanyDetails companyDetails = companyDetailsRepository.findById(companyCode).orElseThrow(
				() -> new CompanyNotFoundException("Company with company code [" + companyCode + "] not found."));

		stockPriceIndexDto.setCompanyDto(DtoEntityUtils.convertToCompanyDetailsDTO(companyDetails));

		List<StockPriceDetailsDTO> stockPriceDetailsList = getStockByCode(companyCode);
		stockPriceIndexDto.setStockPriceList(stockPriceDetailsList);

		Double maxStockPrice = getMaxStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMaxStockPrice(maxStockPrice);

		Double avgStockPrice = getAvgStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setAvgStockPrice(avgStockPrice);

		Double minStockPrice = getMinStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMinStockPrice(minStockPrice);

		return stockPriceIndexDto;
	}

	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findMaxStockPrice(companyCode, startDate, endDate);
	}

	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findAvgStockPrice(companyCode, startDate, endDate);
	}

	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockPriceRepository.findMinStockPrice(companyCode, startDate, endDate);
	}

}
