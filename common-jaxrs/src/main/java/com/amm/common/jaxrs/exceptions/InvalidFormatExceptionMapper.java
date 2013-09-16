package com.amm.common.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/** 
 * JSON/XML validation failure - legal but not valid.
 */
@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {
	public Response toResponse(InvalidFormatException ex) {
		return ExceptionMapperUtils.toResponseInvalidFormat(ex);
	}
}
