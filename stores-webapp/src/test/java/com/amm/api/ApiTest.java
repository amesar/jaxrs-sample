package com.amm.stores.api;

import java.util.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import com.amm.stores.api.dto.stores.Store;
import com.amm.stores.api.dto.common.Location;
import com.amm.stores.api.client.StoresApiClientException;
import com.amm.common.http.exceptions.HttpClientException; 
import com.amm.stores.api.dto.common.CommonDtoTestUtils;

@Test (groups = { "api" })
public class ApiTest extends BaseTest {
	private static Logger logger = Logger.getLogger(ApiTest.class);

	@Test
	public void upsert_And_Get() throws Exception {
		Store store = getRefStore();
		logger.info("store="+store);
		client.upsert(store);
		client.get(refStoreId);
	}

	@Test
	public void upsert_DeleteGet() throws Exception {
		Store store = getRefStore();
		client.upsert(store);
		client.delete(refStoreId);
		client.get(refStoreId);
	}

	@Test
	public void delete_NonExistentStore() throws Exception {
		String storeId = createNonExistentStoreId();
		client.delete(storeId);
	}

	@Test
	public void get_NonExistentStore() throws Exception {
		String storeId = createNonExistentStoreId();
		Store store = client.get(storeId);
		Assert.assertNull(store);
	}

	@Test (expectedExceptions = StoresApiClientException.class)
	public void upsert_MissingStoreId() throws Exception { 
		Store store = getRefStore();
		store.setStoreId(null);
		logger.debug("store="+store);
		client.upsert(store);
	}

	@Test (expectedExceptions = HttpClientException.class) 
	public void upsert_MissingLocation() throws Exception {
		Store store = getRefStore();
		store.setLocation(null);
		client.upsert(store);
	}

	@Test (expectedExceptions = HttpClientException.class) 
	public void upsert_MissingDefaultCurrency() throws Exception {
		Store store = getRefStore();
		store.setDefaultCurrency(null);
		client.upsert(store);
	}

	@Test (expectedExceptions = HttpClientException.class) 
	public void upsert_InvalidDefaultCurrency() throws Exception {
		Store store = getRefStore();
		store.setDefaultCurrency("foobar");
		client.upsert(store);
	}

	@Test (expectedExceptions = HttpClientException.class) 
	public void upsert_MissingPaymentType() throws Exception {
		Store store = getRefStore();
		store.setPaymentType(null);
		client.upsert(store);
	}

	public void upsert_MissingAddress() throws Exception {
		Store store = getRefStore();
		store.setAddress(null);
		client.upsert(store);
	}

/* TODO
	@Test (expectedExceptions = HttpClientException.class) 
	public void upsert_NoCreds() throws Exception {
		Store store = getRefStore();
		clientNoCreds.upsert(store);
	}
*/

}
