package com.wellsfargo.qart.estock.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CompanyDetails")
public class CompanyDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "companyCode")
	private Long companyCode;
	private String stockExchange;
	private String companyName;
	private String companyCEO;
	private Double turnover;
	private String boardOfDirectors;
	private String companyProfile;

	@OneToMany(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "companyCode")
	private Set<StockPriceDetails> StockPriceDetails;

	public CompanyDetails(Long companyCode, String stockExchange, String companyName, String companyCEO,
			Double turnover, String boardOfDirectors, String companyProfile) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.turnover = turnover;
		this.boardOfDirectors = boardOfDirectors;
		this.companyProfile = companyProfile;
	}

}
