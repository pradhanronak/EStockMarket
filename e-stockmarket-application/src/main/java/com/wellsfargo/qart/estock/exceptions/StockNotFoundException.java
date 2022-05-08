package com.wellsfargo.qart.estock.exceptions;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3149606590630559956L;

	public StockNotFoundException(String message) {
		super(message);
	}
}