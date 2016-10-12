package com.appleframework.solr.servlet;

import javax.servlet.Filter;

/**
 * All Solr filters available to the user's webapp should
 * extend this class and not just implement {@link Filter}.
 * This class ensures that the logging configuration is correct
 * before any Solr specific code is executed.
 */
abstract class BaseSolrFilter implements Filter {
  
  static {
    CheckLoggingConfiguration.check();
  }
  
}
