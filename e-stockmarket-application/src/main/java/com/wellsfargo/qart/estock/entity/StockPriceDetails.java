package com.wellsfargo.qart.estock.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "StockPriceDetails")
public class StockPriceDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Id
	private Long Id;

	private Long companyCode;

	@Column(precision = 10, scale = 2)
	private Double currentStockPrice;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate stockPriceDate;

	@Column(columnDefinition = "TIME")
	private LocalTime stockPriceTime;

}
