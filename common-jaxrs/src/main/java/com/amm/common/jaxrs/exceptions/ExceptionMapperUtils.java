package com.amm.common.jaxrs.exceptions;

import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

import com.amm.common.jaxrs.util.ApplicationError;
import com.amm.common.jaxrs.data.Error; 

/**
 * Common logic for custom exception mappers.
 */
public class ExceptionMapperUtils {
	private static final Logger logger = Logger.getLogger(ExceptionMapperUtils.class);

	static public Response toResponseInvalidFormat(Exception ex) {
		logger.debug("-------------------");
		logger.debug("Exception: "+ex);
		logger.debug("Exception.cause: "+ex.getCause());

		String appError = ApplicationError.InvalidFormat.toString() ;
		Response.ResponseBuilder rb = createResponseBuilder(appError, ex);
		return rb.build();
	}

	static Response.ResponseBuilder createResponseBuilder(String appError, Exception ex) {
		int status = 400 ; 
		String emsg = ex.getMessage();
		logger.debug("status="+status+" appError="+appError+" msg="+emsg);

		Error error = new Error(emsg, appError);
		Response.ResponseBuilder rb = Response.status(status);
		if (appError != null) {
			rb.entity(error);
		}
		return rb;
	}

	static public Response toResponseIllegalSyntax(Exception ex) {
		logger.debug("-------------------");
		logger.debug("Exception: "+ex);
		logger.debug("Exception.cause: "+ex.getCause());

		String appError = ApplicationError.IllegalSyntax.toString() ;
		int status = 400 ; 
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
