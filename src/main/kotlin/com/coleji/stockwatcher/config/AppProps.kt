package com.coleji.stockwatcher.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "com.coleji.stockwatcher")
data class AppProps(
	var polygonApiKey: String = ""
)