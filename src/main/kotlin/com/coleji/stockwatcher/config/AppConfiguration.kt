package com.coleji.stockwatcher.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {
	@Bean
	fun appProps(): AppProps {
		return AppProps()
	}
}