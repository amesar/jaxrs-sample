package com.amm.stores.api;

import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;
import com.google.common.io.Files;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.*;
import com.amm.stores.api.client.StoresApiClient ;
import com.amm.stores.api.dto.stores.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BaseTest {
	private static Logger logger = Logger.getLogger(BaseTest.class);
	static StoresApiClient client ;
	//static StoresApiClient clientNoCreds ;
	static String testDir = "src/test/resources/data" ;
	static File refFile = new File(testDir + "/refStore.json") ;
	static String refStoreId ;
	static private ObjectMapper mapper = new ObjectMapper();
	static String refStoreJson ;

	private static String [] configFiles = {
		"appContext-test.xml"
	} ;

	@BeforeSuite
	public static void beforeSuite() throws Exception {
		initSpring();
		refStoreJson = new String(Files.toByteArray(refFile));
		logger.info("store json: "+refStoreJson);
		mapper.disable(SerializationFeature.WRAP_ROOT_VALUE);
		Store refStore = mapper.readValue(refStoreJson, Store.class);
	 	refStoreId = refStore.getStoreId();
	}

	@AfterSuite
	public static void afterSuite() {
	}

	static void initSpring() {
		logger.debug("configFiles="+Arrays.toString(configFiles));
		ApplicationContext context = new ClassPathXmlApplicationContext(configFiles);
		client = context.getBean("storesApiClient",StoresApiClient.class);
		//clientNoCreds = context.getBean("storesApiClientNoCreds",StoresApiClient.class);
	}

	static String createNonExistentStoreId() {
		return "__"+System.currentTimeMillis();
	}

	static Store getRefStore() throws Exception { 
		return mapper.readValue(refStoreJson, Store.class); // TODO: do with clone
	}
}
