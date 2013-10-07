package com.amm.stores.rest;

import java.util.*;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdminResource {
	private static final Logger logger = Logger.getLogger(AdminResource.class);
	private StoreResource storesResource;
	
	public AdminResource(StoreResource storesResource) {
		this.storesResource = storesResource;
	}

	@GET
	@Path("/stores")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response infoStores() throws Exception {
		return storesResource.info();
	}
}
