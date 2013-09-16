package com.amm.common.jaxrs.exceptions.jackson2;

import java.util.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.amm.common.jaxrs.exceptions.ExceptionMapperUtils;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/** 
 * Jackson 2  - Invalid JSON - Legal JSON but validation failed.  
 */
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {
	public Response toResponse(UnrecognizedPropertyException ex) {
		return ExceptionMapperUtils.toResponseInvalidFormat(ex);
	}
}
