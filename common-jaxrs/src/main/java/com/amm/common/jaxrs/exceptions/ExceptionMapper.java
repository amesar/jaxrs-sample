package com.amm.common.jaxrs.exceptions;

import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import com.amm.common.jaxrs.util.ApplicationError;
import com.amm.common.jaxrs.data.Error; 

/** 
 * Top-level exception mapper.
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
	private static final Logger logger = Logger.getLogger(ExceptionMapper.class);

	public Response toResponse(Exception ex) {
		logger.debug("-------------------");
		logger.debug("Exception: "+ex);
		logger.debug("Exception.class: "+ex.getClass().getName());
		Throwable cause = ex.getCause();
		logger.debug("Exception.cause: "+cause);
		if (cause != null) {
			logger.debug("Exception.cause.class: "+cause.getClass().getName());
			if (cause instanceof InvalidFormatException)
				return ExceptionMapperUtils.toResponseInvalidFormat((InvalidFormatException)cause);
		}
		ex.printStackTrace();

		String appError = ApplicationError.GenericServerError.toString() ;
		int status = 500 ; 
		String emsg = ex.getMessage();
		logger.debug("status="+status+" appError="+appError+" msg="+emsg);

		Error error = new Error(emsg, appError);
		Response.ResponseBuilder rb = Response.status(status);
		if (appError != null) {
			rb.entity(error);
		}
		return rb.build();
	}
}
