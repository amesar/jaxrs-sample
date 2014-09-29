package com.amm.stores.api.dto.stores;

import java.util.*;
import java.io.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.google.common.io.Files;
import com.amm.common.util.ValidatorUtils;
import com.amm.common.util.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.amm.stores.api.dto.common.Location;

public class StoreTest {
    String testDir = "src/test/resources/data" ;
    File refFile = new File(testDir + "/refStore.json") ;
    String refStoreId ;
    String refStoreJson ;
    private ObjectMapper mapper = new ObjectMapper();

	@BeforeClass
	public void beforeClass() throws Exception {
        refStoreJson = new String(Files.toByteArray(refFile));
        mapper.disable(SerializationFeature.WRAP_ROOT_VALUE);
	}

	@Test
	public void valid() throws Exception {
    	Store store = getRefStore() ;
		ValidatorUtils.validate(store);
	}

	@Test(expectedExceptions = ValidationException.class)
	public void missingPpHereStoreId() throws Exception {
    	Store store = getRefStore() ;
        store.setStoreId(null);
		ValidatorUtils.validate(store);
	}

	@Test(expectedExceptions = ValidationException.class)
	public void missingPpHereStoreId2() throws Exception {
   		Store store = getRefStore() ;
       	store.setStoreId(null);
		ValidatorUtils.validate(store);
	}

	@Test(expectedExceptions = ValidationException.class)
	public void missingLat() throws Exception {
    	Store store = getRefStore() ;
       	store.getLocation().setLat(null);
		ValidatorUtils.validate(store);
	}

    private Store getRefStore() throws Exception {
        return mapper.readValue(refStoreJson, Store.class); // TODO: do with clone
    }
}
