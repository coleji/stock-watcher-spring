package com.coleji.stockwatcher.model.polygon

import java.time.LocalDate

data class DtoDividendResponse(
	val next_url: String?,
	val request_id: String,
	val status: String,
	val results: List<DtoDividentResponseResult>?
) {
	data class DtoDividentResponseResult(
		val cash_amount: Double,
		val currency: String?,
		val declaration_date: LocalDate,
		val dividend_type: String,
		val ex_dividend_date: LocalDate,
		val frequency: Integer,
		val id: String?,
		val pay_date: LocalDate?,
		val record_date: LocalDate?,
		val ticker: String
	)
}