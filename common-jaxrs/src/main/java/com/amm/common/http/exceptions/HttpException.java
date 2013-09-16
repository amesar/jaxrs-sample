package com.amm.common.http.exceptions;

/**
 * HTTP exception.
 */
public class HttpException extends RuntimeException {

	public HttpException(int statusCode, String reasonPhrase) {
		super("statusCode="+statusCode+" reasonPhrase="+reasonPhrase);
		this.statusCode = statusCode ;
		this.reasonPhrase = reasonPhrase ;
	}

	public HttpException(int statusCode, String reasonPhrase, String msg) {
		super("statusCode="+statusCode+" reasonPhrase=["+reasonPhrase+"] msg=["+msg+"]");
		this.statusCode = statusCode ;
		this.reasonPhrase = reasonPhrase ;
		this.msg = msg ;
	}

	private int statusCode; 
	public int getStatusCode() { return statusCode; }
	public void setStatusCode(int statusCode) { this.statusCode = statusCode; } 
 
	private String reasonPhrase; 
	public String getReasonPhrase() { return reasonPhrase; }
	public void setReasonPhrase(String reasonPhrase) { this.reasonPhrase = reasonPhrase; } 

	private String msg; 
	public String getMsg() { return msg; }
	public void setMsg(String msg) { this.msg = msg; } 
}
