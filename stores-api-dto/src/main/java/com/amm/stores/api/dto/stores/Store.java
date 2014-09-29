package com.amm.stores.api.dto.stores;

import javax.validation.constraints.*;
import javax.validation.*;
import com.fasterxml.jackson.annotation.*;
import com.amm.stores.api.dto.common.Address;
import com.amm.stores.api.dto.common.Location;

// Created: Mon Sep 16 12:49:53 EDT 2013

public class Store {
	@NotNull
	@JsonProperty("store_id")
	private String storeId;
	public String getStoreId() { 
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@NotNull
	@JsonProperty("name")
	private String name;
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("logo_url")
	private String logoUrl;
	public String getLogoUrl() { 
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@JsonProperty("message")
	private String message;
	public String getMessage() { 
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("expire_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private java.util.Date expireDate;
	public java.util.Date getExpireDate() { 
		return expireDate;
	}
	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}

	public static enum MobilityType {
		MOBILE,
		STATIC
	}
	@NotNull
	@JsonProperty("mobility_type")
	private MobilityType mobilityType;
	public MobilityType getMobilityType() { 
		return mobilityType;
	}
	public void setMobilityType(MobilityType mobilityType) {
		this.mobilityType = mobilityType;
	}

	@JsonProperty("phone")
	private String phone;
	public String getPhone() { 
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Valid
	@JsonProperty("address")
	private Address address;
	public Address getAddress() { 
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@NotNull
	@Valid
	@JsonProperty("location")
	private Location location;
	public Location getLocation() { 
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	@NotNull
	@JsonProperty("default_currency")
	private String defaultCurrency;
	public String getDefaultCurrency() { 
		return defaultCurrency;
	}
	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public static enum PaymentType {
		SQR,
		PPL
	}
	@NotNull
	@JsonProperty("payment_type")
	private PaymentType paymentType;
	public PaymentType getPaymentType() { 
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public static enum GratuityType {
		STANDARD
	}
	@JsonProperty("gratuity_type")
	private GratuityType gratuityType;
	public GratuityType getGratuityType() { 
		return gratuityType;
	}
	public void setGratuityType(GratuityType gratuityType) {
		this.gratuityType = gratuityType;
	}

	@Override
	public String toString() {
		return 
			 "storeId="+storeId
			+" name="+name
			+" logoUrl="+logoUrl
			+" message="+message
			+" expireDate="+expireDate
			+" mobilityType="+mobilityType
			+" phone="+phone
			+" address="+address
			+" location="+location
			+" defaultCurrency="+defaultCurrency
			+" paymentType="+paymentType
			+" gratuityType="+gratuityType
			;
	}
}
