package com.wellsfargo.qart.estock.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.qart.estock.entity.StockPriceDetails;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPriceDetails, Long> {

	@Query("select cd FROM StockPriceDetails cd WHERE cd.companyCode=?1")
	public List<StockPriceDetails> findStockByCompanyCode(Long companyCode);

	@Query("SELECT MAX(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findMaxStockPrice(Long companyCode, LocalDate from, LocalDate to);

	@Query("SELECT AVG(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findAvgStockPrice(Long companyCode, LocalDate from, LocalDate to);

	@Query("SELECT MIN(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findMinStockPrice(Long companyCode, LocalDate from, LocalDate to);

}
