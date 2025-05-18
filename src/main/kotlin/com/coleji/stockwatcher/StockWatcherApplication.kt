package com.coleji.stockwatcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.coleji.stockwatcher.config.prop")
class StockWatcherApplication

fun main(args: Array<String>) {
	runApplication<StockWatcherApplication>(*args)
}