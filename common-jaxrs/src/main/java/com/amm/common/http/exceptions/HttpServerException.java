package com.amm.common.http.exceptions;

/**
 * HTTP server exception.
 */
public class HttpServerException extends HttpException {

	public HttpServerException(int statusCode, String reasonPhrase) {
		super(statusCode, reasonPhrase) ;
	}

	public HttpServerException(int statusCode, String reasonPhrase, String msg) {
		super(statusCode, reasonPhrase, msg) ;
	}
}
