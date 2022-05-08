package com.wellsfargo.qart.estock.exceptions;

public class InvalidStockException extends RuntimeException {

	private static final long serialVersionUID = -2715160327040660113L;

	public InvalidStockException(String message) {
		super(message);
	}
}