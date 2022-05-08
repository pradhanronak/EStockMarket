package com.wellsfargo.qart.estock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.exceptions.InvalidCompanyException;
import com.wellsfargo.qart.estock.services.CompanyService;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping(value = "/addCompany")
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails(@Valid @RequestBody CompanyDetailsDTO companyDetailsDTO, BindingResult bindingResult) throws InvalidCompanyException {
		if (bindingResult.hasErrors()) {
			throw new InvalidCompanyException("Company Data entered is not Valid");
		} else {
			return ResponseEntity.ok(companyService.saveCompanyDetails(companyDetailsDTO));
		}
	}

	@DeleteMapping(value = "/deleteCompany/{companyCode}")
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode") Long companyCode) {
		CompanyDetailsDTO companyDetailsDTO = companyService.deleteCompany(companyCode);
		return ResponseEntity.ok(companyDetailsDTO);
	}

	@GetMapping(value = "/getAllCompanies")
	public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {
		List<CompanyDetailsDTO> companyDetailsDTOs = companyService.getAllCompanies();
		return ResponseEntity.ok(companyDetailsDTOs);
	}
}
