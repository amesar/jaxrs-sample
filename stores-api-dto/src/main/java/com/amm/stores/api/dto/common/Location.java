package com.amm.stores.api.dto.common;

import javax.validation.constraints.*;
import javax.validation.*;
import com.fasterxml.jackson.annotation.*;

// Created: Mon Sep 16 11:33:21 EDT 2013

public class Location {
	@NotNull
	@DecimalMin("-90.0")
	@DecimalMax("90.0")
	@JsonProperty("lat")
	private Double lat;
	public Double getLat() { 
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}

	@NotNull
	@DecimalMin("-180.0")
	@DecimalMax("180.0")
	@JsonProperty("lon")
	private Double lon;
	public Double getLon() { 
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return 
			 "lat="+lat
			+" lon="+lon
			;
	}
}
