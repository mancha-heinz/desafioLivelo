package com.gabriel.desafiolivelo.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -7545308215824369074L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
