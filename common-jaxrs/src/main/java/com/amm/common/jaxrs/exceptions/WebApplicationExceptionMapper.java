package com.amm.common.jaxrs.exceptions;

import java.util.*;
import org.apache.log4j.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.xml.sax.SAXParseException;
import com.ctc.wstx.exc.WstxUnexpectedCharException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.JsonParseException;

import com.amm.common.jaxrs.util.ApplicationError;
import com.amm.common.jaxrs.data.Error; 

/** 
 * IllegaSyntax and InvalidFormat.  
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	private static final Logger logger = Logger.getLogger(WebApplicationExceptionMapper.class);
	private static final String SAXParseException = "org.xml.sax.SAXParseException" ;
	private static final String WstxUnexpectedCharException = "com.ctc.wstx.exc.WstxUnexpectedCharException" ;

	public Response toResponse(WebApplicationException ex) {
		logger.debug("-------------------");
		logger.debug("Exception: "+ex);
		logger.debug("Exception.cause: "+ex.getCause());

 		Response response = ex.getResponse() ;
		//dump(response.getMetadata());
		logger.debug("response="+response);
		logger.debug("response.entity="+response.getEntity());

		int status = response.getStatus();
		logger.debug("status.1="+status);

		String appError = null;
		Throwable cause = ex.getCause();
		logger.debug("cause="+cause);

		// WORKAROUND for JSON illegal type for field is 500 instead of 400
		if (cause instanceof JsonMappingException) {
			appError = ApplicationError.InvalidFormat.toString() ;
			status = 400 ; 

		// WORKAROUND for JSON illegal syntax
		} else if (cause instanceof JsonParseException) {
			appError = ApplicationError.IllegalSyntax.toString() ;
			status = 400 ; 

		// XML
		} else if (status == 400 && cause != null ) {
			if (cause instanceof SAXParseException) {
				appError = ApplicationError.InvalidFormat.toString() ;
			} else if (cause instanceof WstxUnexpectedCharException) {
				appError = ApplicationError.IllegalSyntax.toString() ;
			}

		// Java Serialized: if send illegal input
		} else if (status == 500 && (cause instanceof java.io.StreamCorruptedException)) {
			status = 400 ; 
			appError = ApplicationError.IllegalSyntax.toString() ;
		}

		String emsg = ex.getMessage();
		logger.debug("status.2="+status+" appError="+appError+" msg="+emsg);
		Error error = new Error(emsg, appError);
		Response.ResponseBuilder rb = Response.status(status);
		if (appError != null) {
			rb.entity(error);
		}
		//rb.type(MediaType.APPLICATION_XML); // HACK: http://www.mail-archive.com/users@cxf.apache.org/msg08310.html
		return rb.build();
	}

	private void dump(MultivaluedMap<String,Object> map) {
		System.out.println(">> MAP: size="+map.size());
		for (String key : map.keySet() ) {
			System.out.println(">>   key="+key+" val="+map.get(key));
		}
	}
}
