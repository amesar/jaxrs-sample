package com.amm.stores.api.dto.common;

import javax.validation.constraints.*;
import javax.validation.*;
import com.fasterxml.jackson.annotation.*;

// Created: Mon Sep 16 11:33:20 EDT 2013

public class Error {
	@NotNull
	@JsonProperty("error")
	private String error;
	public String getError() { 
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return 
			 "error="+error
			;
	}
}
