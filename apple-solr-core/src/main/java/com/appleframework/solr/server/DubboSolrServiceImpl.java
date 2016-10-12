package com.appleframework.solr.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;

import com.appleframework.solr.core.CoreContainerManager;
import com.appleframework.solr.solrj.DubboSolrService;

public class DubboSolrServiceImpl implements DubboSolrService {

	private static Map<String, EmbeddedSolrServer> serverMap = new HashMap<>();
	
	private EmbeddedSolrServer getServer(String corename) {
		EmbeddedSolrServer server = serverMap.get(corename);
		if(null == server) {
			CoreContainer coreContainer = CoreContainerManager.getCores();
			server = new EmbeddedSolrServer(coreContainer, corename);
			serverMap.put(corename, server);
		}
		return server;
	}

	public byte[] query(String corename, byte[] bytes) throws SolrServerException, IOException {
		try {
			SolrQuery q = (SolrQuery)SerializationUtils.deserialize(bytes);
			QueryResponse response = getServer(corename).query(q);
			return SerializationUtils.serialize(response);
		} catch (Exception e) {
			throw e;
		}
	}

}
