package com.appleframework.solr.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

public interface DubboSolrService {
	
	public byte[] query(String corename, byte[] bytes) throws SolrServerException, IOException;
	
}
