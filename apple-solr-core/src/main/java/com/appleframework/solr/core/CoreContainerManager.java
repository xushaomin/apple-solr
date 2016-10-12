package com.appleframework.solr.core;

import org.apache.solr.core.CoreContainer;

public class CoreContainerManager {

	public static volatile CoreContainer cores;

	public static CoreContainer getCores() {
		return cores;
	}

	public static void setCores(CoreContainer cores) {
		CoreContainerManager.cores = cores;
	}

	public static void destroy() {
		if (cores != null) {
			try {
				cores.shutdown();
			} finally {
				cores = null;
			}
		}
	}

}
