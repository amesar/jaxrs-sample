package com.amm.common.jaxrs.exceptions;

/**
 * Base parsing exception - illegal syntax - not legal JSON nor XML.
 */
public class ParsingException extends Exception {
	public ParsingException() {
	}

	public ParsingException(String msg) {
		super(msg);
	}

	public ParsingException(Throwable throwable) {
		super(throwable);
	}

	public ParsingException(String message, Throwable throwable) {
		super(message,throwable);
	}
}
