package com.appleframework.solr.solrj.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.SerializationUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.appleframework.solr.solrj.DubboSolrService;
import com.appleframework.solr.solrj.utils.ApplicationContextUtility;

public class DubboSolrClient {

	private String collection;

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	public DubboSolrClient(String collection) {
		super();
		this.collection = collection;
	}
	
	public DubboSolrClient() {
		super();
	}

	public QueryResponse query(SolrQuery query) throws SolrServerException, IOException {
		long startTime = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
		DubboSolrService service = (DubboSolrService)ApplicationContextUtility.getBean("dubboSolrService");
		byte[] bytes = service.query(collection, SerializationUtils.serialize(query));
		QueryResponse response = (QueryResponse)SerializationUtils.deserialize(bytes);
		long endTime = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
		response.setElapsedTime(endTime - startTime);
		return response;
	}
}
