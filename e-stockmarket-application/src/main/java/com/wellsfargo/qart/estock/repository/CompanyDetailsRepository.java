package com.wellsfargo.qart.estock.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.qart.estock.entity.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long> {

	@Query("select cd FROM CompanyDetails cd WHERE cd.companyCode=?1")
	public CompanyDetails findCompanyDetailsById(Long companyCode);

	@Transactional
	@Modifying
	@Query("delete FROM CompanyDetails de WHERE de.companyCode=?1")
	public Integer deleteByCompanyCode(Long companyCode);
}
