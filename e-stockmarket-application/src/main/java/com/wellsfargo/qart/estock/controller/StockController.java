package com.wellsfargo.qart.estock.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.qart.estock.dto.StockPriceDetailsDTO;
import com.wellsfargo.qart.estock.dto.StockPriceIndexDTO;
import com.wellsfargo.qart.estock.exceptions.CompanyNotFoundException;
import com.wellsfargo.qart.estock.exceptions.InvalidStockException;
import com.wellsfargo.qart.estock.exceptions.StockNotFoundException;
import com.wellsfargo.qart.estock.services.StockService;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(value = "/addStock")
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(
			@Valid @RequestBody StockPriceDetailsDTO stockPriceDetailsDTO, BindingResult bindingResult)
			throws InvalidStockException {
		if (bindingResult.hasErrors()) {
			throw new InvalidStockException("Stock Data entered is not Valid");
		} else {
			return ResponseEntity.ok(stockService.saveStockPriceDetails(stockPriceDetailsDTO));
		}
	}

	@GetMapping(value = "/getStockByCompanyCode/{companyCode}")
	public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable Long companyCode) {
		List<StockPriceDetailsDTO> list = stockService.getStockByCode(companyCode);
		if (list == null) {
			throw new CompanyNotFoundException("Company with company code [" + companyCode + "] not found.");
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable Long companyCode,
			@PathVariable Date startDate, @PathVariable Date endDate) {
		StockPriceIndexDTO stockPriceIndexDTO = stockService.getStockPriceIndex(companyCode, startDate.toLocalDate(),
				endDate.toLocalDate());
		if (stockPriceIndexDTO == null) {
			throw new StockNotFoundException("Invalid Company Code or Date!!! Please enter valid Details...");
		} else {
			return ResponseEntity.ok(stockPriceIndexDTO);
		}
	}

}
