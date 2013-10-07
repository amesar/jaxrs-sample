package com.amm.stores.api.dto.common;

import java.util.*;
import org.testng.Assert;
import org.testng.annotations.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;

/**
 * Reusable accorss multiple modules - api-dto and webapp for API tests too.
 */
public class CommonDtoTestUtils {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private static double [] [] invalidLocs = { { 942,71 }, {42,971}, { 942,971 }, {-999,999} } ;

	private static double [] [] validLocs = { { 42,71 }, {42,-71}, { -42,71 }, {-42,-71}, {-90,90} } ;
	// {0,0}  // TODO: Atlas croaks on but this is a valid GP in Gulf of Guinea!

	public static List<Location> getInvalidLocations() {
		List<Location> list = new ArrayList<Location>();
		for (double [] locs : invalidLocs) 
			list.add(newLocation(locs[0],locs[1]));
		return list;
	}

	public static List<Location> getValidLocations() {
		List<Location> list = new ArrayList<Location>();
		for (double [] locs : validLocs) 
			list.add(newLocation(locs[0],locs[1]));
		return list;
	}

	public static Location newLocation(double lat, double lng) {
		Location loc = new Location();
		loc.setLat(lat);
		loc.setLng(lng);
		return loc;
	}
}
