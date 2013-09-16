package com.amm.common.jaxrs.data;

/**
 * Error object to be returned in HTTP response body when an error HTTP status code occurs.
 */
public class Error {
	public Error() {
	}

	public Error(String message) {
		this.message = message ;
	}

	public Error(String message, String code) {
		this.message = message ;
		this.code = code ;
	}

	private String code; 
	/** Symbolic application code. */
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; } 

	private String message;
	/** Human-friendly message. */
	public String getMessage() { return message; }
	public void setMessage(String val) { message=val; } 

	@Override
	public String toString() { 
		return
			  "code="+code
			+ " message="+message
			;
	}
}
