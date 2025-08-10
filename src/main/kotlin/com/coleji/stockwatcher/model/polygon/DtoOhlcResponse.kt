package com.coleji.stockwatcher.model.polygon

data class DtoOhlcResponse (
	val adjusted: Boolean,
	val queryCount: Integer,
	val request_id: String,
	val resultsCount: Integer,
	val status: String,
	val count: Integer?,
	val results: List<DtoOhlcResponseResult>?,
) {
	data class DtoOhlcResponseResult (
		val T: String,
		val c: Double,
		val h: Double,
		val l: Double,
		val n: Long?,
		val o: Double,
		val otc: Boolean?,
		val t: Long,
		val v: Double,
		val vw: Double?
	)
}