package com.amm.stores.rest;

import java.util.*;
import org.apache.log4j.Logger;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import javax.ws.rs.core.Response;

/**
 * CXF authentication handler.
 */
public class AuthenticationHandler implements RequestHandler {
	private static final Logger logger = Logger.getLogger(AuthenticationHandler.class);
	private Map<String, String> credentialsMap = new HashMap<String, String>();

	public AuthenticationHandler(Map<String, String> credentialsMap) {
		this.credentialsMap = credentialsMap;
	}
 
	public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
		AuthorizationPolicy policy = (AuthorizationPolicy)m.get(AuthorizationPolicy.class);
		if (policy == null)
			return null; // TODO: check if we want to do this in real life
		String username = policy.getUserName();
		String password = policy.getPassword(); 
		logger.debug("username="+username);
		logger.debug("password="+password);
		if (isAuthenticated(username, password)) {
			return null;
		} else {
			// authentication failed, request the authetication, add the realm name if needed to the value of WWW-Authenticate 
			return Response.status(401).header("WWW-Authenticate", "Basic").build();
		}
	}

	private boolean isAuthenticated(String username, String password) {
		String p = credentialsMap.get(username);
		if (p == null) return false;
		return p.equals(password) ;
	}
}
