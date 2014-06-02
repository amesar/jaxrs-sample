package com.amm.stores.provider;

import java.util.*;
import com.amm.stores.api.dto.stores.Store;

public interface StoreProvider {

	public void upsert(Store store) throws Exception ;

	public void delete(String ppHereStoreId) throws Exception ;

	public Store get(String storeId) throws Exception ;
}
