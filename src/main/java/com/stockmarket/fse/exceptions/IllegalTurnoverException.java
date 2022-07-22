package com.stockmarket.fse.exceptions;

public class IllegalTurnoverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalTurnoverException(String msg) {
		super(msg);
	}
	
	public IllegalTurnoverException() {
		super("Minimum Turnover Must be 100000000");
	}
}
