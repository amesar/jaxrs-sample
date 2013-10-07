package com.amm.stores.service;

import java.util.*;
import org.apache.log4j.Logger;
import com.amm.stores.util.Loggers;
import com.amm.stores.api.dto.stores.Store;
import com.amm.stores.provider.StoreProvider;
import com.amm.common.jaxrs.exceptions.InvalidFormatException; // TODO: abstraction leakage!

// TODO: use aspects for logging exceptions
/**
 * Business service for stores.
 */
public class StoreService {
	private static final Logger logger = Logger.getLogger(StoreService.class);
	private final StoreProvider storeProvider ;
	private final StoreValidator storeValidator ; 

	public StoreService(StoreProvider storeProvider, StoreValidator storeValidator) {
		this.storeProvider = storeProvider ;
		this.storeValidator = storeValidator ;
	}

	public Store upsert(Store store) throws Exception {
	   	List<String> errors = storeValidator.validate(store);
	   	if (errors.size() > 0) {
			String emsg = "CLIENT: upsert: ["+errors.get(0)+"] store="+store;
			Loggers.REQUESTS.error(emsg);
			logger.error(emsg);
		   	throw new InvalidFormatException(errors);
		}
		try {
			Store store2 = storeProvider.upsert(store);
			Loggers.REQUESTS.info("upsert: store="+store);
			return store2; // TODO: perhaps validate this one too? Not clear what biz need is for this.
		} catch (Exception e) {
			String emsg = "SERVER: upsert: ["+e.getMessage()+"] store="+store;
			Loggers.REQUESTS.error(emsg);
			logger.error(emsg);
			throw e ;
		}
	}

	public void delete(String storeId) throws Exception {
		try {
			storeProvider.delete(storeId);
			Loggers.REQUESTS.info("delete: storeId="+storeId);
		} catch (Exception e) {
			Loggers.REQUESTS.error("delete: storeId="+storeId);
			throw e ;
		}
	}

	public Store get(String storeId) throws Exception {
		try {
			Store store = storeProvider.get(storeId);
			Loggers.REQUESTS.info("get: storeId="+storeId+" store="+store);
			return store;
		} catch (Exception e) {
			Loggers.REQUESTS.error("get: storeId="+storeId);
			throw e ;
		}
	}

	@Override 
	public String toString() {
		return "provider="+storeProvider.getClass().getName()+" - "+storeProvider;
	}
}
