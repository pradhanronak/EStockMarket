package com.wellsfargo.qart.estock.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;
import com.wellsfargo.qart.estock.entity.CompanyDetails;
import com.wellsfargo.qart.estock.exceptions.CompanyNotFoundException;
import com.wellsfargo.qart.estock.repository.CompanyDetailsRepository;
import com.wellsfargo.qart.estock.services.CompanyService;
import com.wellsfargo.qart.estock.utils.DtoEntityUtils;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDetailsRepository repository;

	@Override
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		CompanyDetails companyDetails = repository.save(DtoEntityUtils.convertToCompanyDetails(companyDetailsDTO));
		return DtoEntityUtils.convertToCompanyDetailsDTO(companyDetails);

	}

	@Override
	public CompanyDetailsDTO deleteCompany(Long companyCode) {
		CompanyDetails companyDetails = repository.findById(companyCode).orElseThrow(
				() -> new CompanyNotFoundException("Company with company code [" + companyCode + "] not found."));

		repository.deleteById(companyCode);
		return DtoEntityUtils.convertToCompanyDetailsDTO(companyDetails);
	}

	@Override
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		CompanyDetails companyDetails = repository.findById(companyCode).orElseThrow(
				() -> new CompanyNotFoundException("Company with company code [" + companyCode + "] not found."));

		return DtoEntityUtils.convertToCompanyDetailsDTO(companyDetails);
	}
	
	@Override
	public List<CompanyDetailsDTO> getAllCompanies() {
		List<CompanyDetails> companyDetailsList = repository.findAll();
		return DtoEntityUtils.convertToCompanyDetailsDtoList(companyDetailsList);
	}

}
