package com.appleframework.solr.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface LocalSolrService {
	
	public QueryResponse query(String corename, SolrQuery q) throws SolrServerException, IOException;
	
}
