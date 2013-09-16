package com.amm.common.jaxrs.util;

import java.util.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import com.amm.common.jaxrs.data.Error;

/**
 * JAX-RS Response utilities.
 */
public class ResponseUtils {
	public static final String HEADER_LOCATION = "Location" ;

	public static Response createGet(Object entity) {
		Response.ResponseBuilder rb = Response.status(Response.Status.OK);
		rb.entity(entity);
		Response r = rb.build();
		return r;
	}

	public static Response createPost(Object entity) {
		Response.ResponseBuilder rb = Response.status(Response.Status.CREATED);
		rb.entity(entity);
		return rb.build();
	}

	public static Response createPost(String resourceUrl, String id) {
		return createPost(resourceUrl, id, null) ;
	}

	public static Response createPost(String resourceUrl, String id, Object entity) {
		Response.ResponseBuilder rb = Response.status(Response.Status.CREATED);
		if (entity != null)
			rb.entity(entity);
		rb.header(HEADER_LOCATION, resourceUrl + "/" + id);
		return rb.build();
	}

	public static Response createPut() {
		Response r = Response.ok().build();
		return r;
	}

	public static Response createPut(Object entity) {
		Response.ResponseBuilder rb = Response.status(Response.Status.OK);
		rb.entity(entity);
		return rb.build();
	}


	public static Response createClientError(String msg) {
		return createClientError(null, msg, Response.Status.BAD_REQUEST);
	}
	public static Response createClientError(String applicationCode, String msg) {
		return createClientError(applicationCode, msg, Response.Status.BAD_REQUEST);
	}
	public static Response createClientError(String applicationCode, String msg, Response.Status status) {
		Response.ResponseBuilder rb = Response.status(status);
		Error error = new Error();
		error.setMessage(msg);
		if (null != applicationCode) {
			error.setCode(applicationCode);
		}
		
		rb.entity(error);
		return rb.build();
	}

	public static Response createServerError(String applicationCode, String msg, Response.Status status) {
		Response.ResponseBuilder rb = Response.status(status);

		Error error = new Error();
		error.setMessage(msg);
		if (null != applicationCode) {
			error.setCode(applicationCode);
		}
		rb.entity(error);
		return rb.build();
	}
	
	public static Response createServerError(String msg) {
		Response.ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		Error error = new Error();
		error.setMessage(msg);
		rb.entity(error);
		return rb.build();
	}

	public static Response createError(Response.Status status, String msg) {
		Response.ResponseBuilder rb = Response.status(status);
		return rb.build();
	}

	static public String getHeader(Response response, String headerName) {
		MultivaluedMap<String,Object> map = response.getMetadata();
		if (map == null) return null ;

		Object val = map.get(headerName);
		if (!(val instanceof ArrayList)) return null;
		ArrayList list = (ArrayList)val;
		if (list.size() == 0) return null ;
		Object obj = list.get(0);
		if (!(obj instanceof String)) return null;
		String headerValue = (String)obj ;
		return headerValue ;
	}

	static public String getCreatedId(Response response) {
		String url = getHeader(response, HEADER_LOCATION);
		if (url == null) return null ;
		int idx = url.lastIndexOf("/");
		if (idx == -1) return null ;
		String id = url.substring(idx+1, url.length());
		return id ;
	}
}
