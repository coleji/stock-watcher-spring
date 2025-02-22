package com.coleji.stockwatcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockWatcherApplication

fun main(args: Array<String>) {
	runApplication<StockWatcherApplication>(*args)
}