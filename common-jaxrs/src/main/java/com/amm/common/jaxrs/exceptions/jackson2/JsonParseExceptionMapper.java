package com.amm.common.jaxrs.exceptions.jackson2;

import java.util.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.amm.common.jaxrs.exceptions.ExceptionMapperUtils;
import com.fasterxml.jackson.core.JsonParseException;

/** 
 * Jackson 2  - Illegal syntax JSON.
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
	public Response toResponse(JsonParseException ex) {
		return ExceptionMapperUtils.toResponseIllegalSyntax(ex);
	}
}
