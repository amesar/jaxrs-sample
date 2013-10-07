package com.amm.stores.api.client;

import java.util.*;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.amm.common.http.exceptions.HttpException;
import com.amm.common.http.exceptions.HttpClientException;
import com.amm.common.http.exceptions.HttpServerException;
import com.amm.stores.api.dto.stores.Store;

/**
 * Client for Stores API.
 */
public class StoresApiClient {
	private static final Logger logger = Logger.getLogger(StoresApiClient.class);
	private HttpClient httpClient;
	private String baseUrl;
	private ObjectMapper mapper = new ObjectMapper();

	public StoresApiClient(String baseUrl, String username, String password) throws Exception {
		this(baseUrl, username, password, new HttpClient());
	}

	public StoresApiClient(String baseUrl, String username, String password, HttpClient httpClient) throws Exception {
		logger.debug("url="+baseUrl);
		logger.debug("username="+username+" password="+password);
		this.baseUrl = baseUrl;
		this.httpClient = httpClient;

		mapper.disable(SerializationFeature.WRAP_ROOT_VALUE);
		Credentials credentials = new UsernamePasswordCredentials(username, password);
		URL url = new URL(baseUrl);
		httpClient.getState().setCredentials(new AuthScope(url.getHost(), url.getPort(), AuthScope.ANY_REALM), credentials);
	}

	public Store get(String storeId) throws Exception {
		String url = makeUrl(storeId);
		GetMethod method = new GetMethod(url);
		adjust(method);
		logger.debug("url="+url);

		try {
			long startTime = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(method);
			long length = method.getResponseContentLength();
			long time = System.currentTimeMillis()-startTime;
			logger.debug("get: url="+url+" status="+statusCode+" bytes="+length+" time="+time);
			if (404 == statusCode) {
				return null;
			}
			checkError(statusCode, method) ;
			String content = method.getResponseBodyAsString();
			//logger.debug("response: "+content);
			Store store = mapper.readValue(content, Store.class);
			return store ;
		} finally {
			method.releaseConnection();
		}
	}

	public Store upsert(Store store) throws Exception {
		if (store.getStoreId() == null)
			throw new StoresApiClientException("StoresApiClient: StoreId is required to form resource URL");
		String url = makeUrl(store.getStoreId());
		PutMethod method = new PutMethod(url);
		adjust(method);
		logger.info("url="+url);
		logger.info("store="+store);

		try {
			long startTime = System.currentTimeMillis();
			if (store != null) {
				String json = mapper.writeValueAsString(store);
				method.setRequestEntity(new StringRequestEntity(json));
			}
			int statusCode = httpClient.executeMethod(method);
			long length = method.getResponseContentLength();
			long time = System.currentTimeMillis()-startTime;
			logger.info("url="+url+" status="+statusCode+" bytes="+length+" time="+time);
			checkError(statusCode, method) ;
			
			String content = method.getResponseBodyAsString();
			//logger.debug("response: "+content);
			return mapper.readValue(content, Store.class);
		} finally {
			method.releaseConnection();
		}
	}

	public void delete(String storeId) throws Exception {
		String url = makeUrl(storeId);
		DeleteMethod method = new DeleteMethod(url);
		try {
			int statusCode = httpClient.executeMethod(method);
			method.getResponseContentLength();
			logger.debug("url="+url+" status="+statusCode);
			checkError(statusCode, method) ;
		} finally {
			method.releaseConnection();
		}
	}

	private String makeUrl(String storeId) {
		return baseUrl + "/" + storeId + "?request_id=foo";
	}

	private void setHeaders(HttpMethodBase method) {
		method.addRequestHeader(new Header("Content-type","application/json"));
	}

	private boolean isError(int statusCode) {
		return statusCode < 200 || statusCode > 299 ;
	}

	private void checkError(int statusCode, HttpMethodBase method) throws Exception {
		if (isError(statusCode)) {
			String ebody = method.getResponseBodyAsString();
			String emsg = "Url="+method.getURI()+" Error="+ebody;
			if (statusCode >= 400 && statusCode <= 499)
				throw new HttpClientException(statusCode,method.getStatusLine().getReasonPhrase(),emsg);
			if (statusCode >= 500 && statusCode <= 599)
				throw new HttpServerException(statusCode,method.getStatusLine().getReasonPhrase(),emsg);
			throw new HttpException(statusCode,method.getStatusLine().getReasonPhrase(),emsg);
		}
	}

	private static String CONTENT_TYPE = "application/json" ;

	private void adjust(HttpMethodBase method) {
		method.addRequestHeader(new Header("Accept",CONTENT_TYPE));
		method.addRequestHeader(new Header("content-type",CONTENT_TYPE));
	}

	@Override
	public String toString() {
		return "baseUrl="+baseUrl;
	}
}
