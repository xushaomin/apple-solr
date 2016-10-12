package com.appleframework.solr.solrj.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.appleframework.solr.solrj.LocalSolrService;
import com.appleframework.solr.solrj.utils.ApplicationContextUtility;

public class LocalSolrClient {

	private String collection;

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	public LocalSolrClient(String collection) {
		super();
		this.collection = collection;
	}
	
	public LocalSolrClient() {
		super();
	}

	public QueryResponse query(SolrQuery query) throws SolrServerException, IOException {
		long startTime = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
		LocalSolrService service = (LocalSolrService)ApplicationContextUtility.getBean("localSolrService");
		QueryResponse response = service.query(collection, query);
		long endTime = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
		response.setElapsedTime(endTime - startTime);
		return response;
	}
}
