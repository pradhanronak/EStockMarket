package com.wellsfargo.qart.estock.services;

import com.wellsfargo.qart.estock.dto.CompanyDetailsDTO;

public interface CompanyService {

	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO);
	
	public CompanyDetailsDTO deleteCompany(Long companyCode);

	public CompanyDetailsDTO getCompanyInfoById(Long companyCode);

}
