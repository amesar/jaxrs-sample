package com.amm.common.jaxrs.exceptions.jackson2;

import java.util.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.amm.common.jaxrs.exceptions.ExceptionMapperUtils;
import com.fasterxml.jackson.databind.JsonMappingException;

/** 
 * Jackson 2  - Invalid JSON - Legal JSON but validation failed - DateTime @JsonFormat pattern fails.
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
	public Response toResponse(JsonMappingException ex) {
		return ExceptionMapperUtils.toResponseInvalidFormat(ex);
	}
}
