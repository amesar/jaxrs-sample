package com.amm.common.http.exceptions;

/**
 * HTTP client exception.
 */
public class HttpClientException extends HttpException {

	public HttpClientException(int statusCode, String reasonPhrase) {
		super(statusCode, reasonPhrase) ;
	}

	public HttpClientException(int statusCode, String reasonPhrase, String msg) {
		super(statusCode, reasonPhrase, msg) ;
	}
}
