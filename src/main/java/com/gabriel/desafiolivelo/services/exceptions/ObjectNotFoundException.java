package com.gabriel.desafiolivelo.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7545308215824369074L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
