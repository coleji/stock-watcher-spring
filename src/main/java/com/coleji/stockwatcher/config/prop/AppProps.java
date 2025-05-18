package com.coleji.stockwatcher.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.coleji.stockwatcher")
public class AppProps {
	private String polygonApiKey;

	public AppProps(String polygonApiKey) {
		this.polygonApiKey = polygonApiKey;
	}

	public String getPolygonApiKey() {
		return polygonApiKey;
	}
}
