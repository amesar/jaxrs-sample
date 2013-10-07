package com.amm.stores.api.dto.common;

import java.util.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.amm.common.util.ValidatorUtils;
import com.amm.common.util.ValidationException;

public class LocationTest extends BaseTest {

	@DataProvider(name = "invalid")
	private Object[][] invalid() throws Exception {
		return createObjects(CommonDtoTestUtils.getInvalidLocations());
	}

	@DataProvider(name = "valid")
	private Object[][] valid() throws Exception {
		return createObjects(CommonDtoTestUtils.getValidLocations());
	}

	private Object[][] createObjects(List<Location> list) throws Exception {
		Object[][] objects = new Object[list.size()][1];
		int j=0;
		for (Location loc : list)
			objects[j++][0] = loc;
		return objects;
	}

	@Test(dataProvider = "invalid", expectedExceptions = ValidationException.class)
	public void expectFailures(Location loc) throws Exception {
		ValidatorUtils.validate(loc);
	}

	@Test(dataProvider = "valid")
	public void expectSuccess(Location loc) throws Exception {
		ValidatorUtils.validate(loc);
	}
}
