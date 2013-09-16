package com.amm.common.jaxrs.exceptions.jaxb;

import com.amm.common.jaxrs.util.ApplicationError;
import com.amm.common.jaxrs.data.Error;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.xml.bind.JAXBException;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/** 
 * JAXB exception
 * Not called? 
 */
@Provider
public class JaxbExceptionMapper implements ExceptionMapper<JAXBException> {
	private static final Logger logger = Logger.getLogger(JaxbExceptionMapper.class);

	public Response toResponse(JAXBException ex) {
		logger.debug("Exception: "+ex);
		String emsg = ex.getMessage();
		Error error = new Error(emsg, ApplicationError.InvalidFormat.toString());
		Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
		rb.entity(error);
		return rb.build();
	}
}
