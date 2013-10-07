package com.amm.stores.service;

import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import com.amm.stores.api.dto.stores.Store;
import com.amm.common.util.ValidatorUtils;

/**
 * Validate syntactic and semantic constraints for a store.
 */
public class StoreValidator {
	private static final Logger logger = Logger.getLogger(StoreValidator.class);
	private Set<String> currencyCodes = new HashSet<String>();

	public StoreValidator(Resource resourceCurrencyCodes) throws IOException {
		InputStream stream = resourceCurrencyCodes.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str ;
		for(int j=0 ; (str = reader.readLine()) != null ; j++) {
			currencyCodes.add(str);
		}
		stream.close();
		logger.debug("#currencyCodes="+currencyCodes.size());
	}

	public List<String> validate(Store store) {
	   	List<String> errors = ValidatorUtils.getErrors(store);
		if (!currencyCodes.contains(store.getDefaultCurrency()))
			errors.add("Illegal ISO 4217 currency code: "+store.getDefaultCurrency());
		return errors;
	}

	@Override 
	public String toString() {
		return "#currencyCodes="+currencyCodes.size();
	}
}
