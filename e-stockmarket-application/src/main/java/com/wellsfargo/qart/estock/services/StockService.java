package com.wellsfargo.qart.estock.services;

import java.time.LocalDate;
import java.util.List;

import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;

public interface StockService {

	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO);

	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode);

	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate);

}
