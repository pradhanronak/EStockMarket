package com.wellsfargo.qart.estock.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(value = "/addStock")
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(@Valid @RequestBody StockPriceDetailsDTO stockPriceDetailsDTO, BindingResult bindingResult) throws InvalidStockException {
		if (bindingResult.hasErrors()) {
			throw new InvalidStockException("Stock Data entered is not Valid - " + bindingResult.getFieldError().getDefaultMessage());
		} else
			return new ResponseEntity<StockPriceDetailsDTO>(stockService.saveStockPriceDetails(stockPriceDetailsDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStockByCompanyCode/{companyCode}")
	public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable Long companyCode) {
		List<StockPriceDetailsDTO> list = stockService.getStockByCode(companyCode);
		if( list == null)
			throw new CompanyNotFoundException("Company with company code [" + companyCode + "] not found.");
		else
			return new ResponseEntity<List<StockPriceDetailsDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable Long companyCode, @PathVariable Date startDate, @PathVariable Date endDate) {
		if(stockService.getStockPriceIndex(companyCode, startDate.toLocalDate(), endDate.toLocalDate()) == null)
			throw new StockNotFoundException("Invalid Company Code or Date!!! Please enter valid Details...");
		else	
			return new ResponseEntity<StockPriceIndexDTO>(stockService.getStockPriceIndex(companyCode, startDate.toLocalDate(), endDate.toLocalDate()), HttpStatus.OK);
	}

}
