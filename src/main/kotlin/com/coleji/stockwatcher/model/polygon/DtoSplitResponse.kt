package com.coleji.stockwatcher.model.polygon

import java.time.LocalDate

data class DtoSplitResponse(
	val status: String,
	val request_id: String,
	val next_url: String?,
	val results: List<DtoSplitResponseResult>
) {
	data class DtoSplitResponseResult(
		val execution_date: LocalDate,
		val split_from: Int,
		val split_to: Int,
		val ticker: String
	)
}