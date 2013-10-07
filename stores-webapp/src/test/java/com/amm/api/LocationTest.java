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

/**
 * Tests for valid and invalid locations.
 */
@Test (groups = { "api" })
public class LocationTest extends BaseTest {
	private static Logger logger = Logger.getLogger(LocationTest.class);

	@DataProvider(name = "invalid")
	private Object[][] invalid() throws Exception {
		return createObjects(CommonDtoTestUtils.getInvalidLocations());
	}
	@DataProvider(name = "valid")
	private Object[][] valid() throws Exception {
		return createObjects(CommonDtoTestUtils.getValidLocations());
	}

	private Object[][] createObjects(List<Location> locations) throws Exception {
		List<StoreWrapper> stores = new ArrayList<StoreWrapper>();
		for (Location loc : locations ) {
			Store store = getRefStore();
			store.setLocation(loc);
			stores.add(new StoreWrapper(store));
		}
		Object[][] objects = new Object[stores.size()][1];
		int j=0;
		for (StoreWrapper store : stores)
			objects[j++][0] = store;
		return objects;
	}


	@Test (dataProvider = "invalid", expectedExceptions = HttpClientException.class) 
	public void expectFailedLocation(StoreWrapper wstore) throws Exception {
		client.upsert(wstore.store);
	}

	@Test(dataProvider = "valid")
	public void expectSuccessfulLocation(StoreWrapper wstore) throws Exception {
		client.upsert(wstore.store);
	}

	// to get location info as test info in report 
	class StoreWrapper {
		Store store;
		StoreWrapper(Store store) { this.store = store ; }
		@Override 
		public String toString() {
			return store.getLocation().toString();
		}
	}
}
