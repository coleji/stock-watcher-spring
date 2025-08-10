package com.coleji.stockwatcher.model.polygon

import java.time.LocalDate

data class DtoFinancialsResponse (
	val count: Integer?,
	val next_url: String?,
	val request_id: String,
	val status: String,
	val results: List<DtoFinancialsResponseResult>?
) {
	data class DtoFinancialsResponseResult (
		val acceptance_datetime: String?,
		val cik: String,
		val company_name: String,
		val end_date: LocalDate?,
		val filing_date: LocalDate?,
		val financials: DtoFinancialsResponseResultFinancials,
		val fiscal_period: String,
		val fiscal_year: Integer?,
		val sic: String?,
		val source_filing_file_url: String?,
		val source_filing_url: String?,
		val start_date: LocalDate?,
		val tickers: List<String>?,
		val timeframe: String,
		val id: String?
	) {

	}

	data class DtoFinancialsResponseResultFinancials (
		val balance_sheet: Map<String, DtoFinancialsIndividualItem>?,
		val cash_flow_statement: Map<String, DtoFinancialsIndividualItem>?,
		val comprehensive_income: Map<String, DtoFinancialsIndividualItem>?,
		val income_statement: Map<String, DtoFinancialsIndividualItem>?
	)

	data class DtoFinancialsIndividualItem (
		val value: Double,
		val unit: String,
		val label: String,
		val order: Integer
	)
}