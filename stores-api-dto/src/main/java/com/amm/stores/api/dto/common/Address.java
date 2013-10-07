package com.amm.stores.api.dto.common;

import javax.validation.constraints.*;
import javax.validation.*;
import com.fasterxml.jackson.annotation.*;

// Created: Mon Sep 16 11:33:19 EDT 2013

public class Address {
	@JsonProperty("street_line_1")
	private String streetLine1;
	public String getStreetLine1() { 
		return streetLine1;
	}
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}

	@JsonProperty("street_line_2")
	private String streetLine2;
	public String getStreetLine2() { 
		return streetLine2;
	}
	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}

	@JsonProperty("city_name")
	private String cityName;
	public String getCityName() { 
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("state_code")
	private String stateCode;
	public String getStateCode() { 
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@JsonProperty("zip_code")
	private String zipCode;
	public String getZipCode() { 
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonProperty("country_code")
	private String countryCode;
	public String getCountryCode() { 
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return 
			 "streetLine1="+streetLine1
			+" streetLine2="+streetLine2
			+" cityName="+cityName
			+" stateCode="+stateCode
			+" zipCode="+zipCode
			+" countryCode="+countryCode
			;
	}
}
