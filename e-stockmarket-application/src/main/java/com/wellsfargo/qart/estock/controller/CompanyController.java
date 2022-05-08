package com.wellsfargo.qart.estock.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.exceptions.CompanyNotFoundException;
import com.wellsfargo.qart.estock.exceptions.InvalidCompanyException;
import com.wellsfargo.qart.estock.services.CompanyService;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping(value = "/addCompany")
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails(@Valid @RequestBody CompanyDetailsDTO companyDetailsDTO, BindingResult bindingResult) throws InvalidCompanyException {
		if (bindingResult.hasErrors()) {
			throw new InvalidCompanyException("Company Data entered is not Valid - " + bindingResult.getFieldError().getDefaultMessage());
		} else
			return new ResponseEntity<CompanyDetailsDTO>(companyService.saveCompanyDetails(companyDetailsDTO), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteCompany/{companyCode}")
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode") Long companyCode) {
		if(companyService.deleteCompany(companyCode) == null)
			throw new CompanyNotFoundException("Company with company code [" + companyCode + "] not found.");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyService.deleteCompany(companyCode), HttpStatus.OK);
	}

}
