package com.coleji.stockwatcher.model.dto

import org.jooq.generated.tables.records.SPSplitsRecord
import java.time.LocalDate

data class DtoPolygonSplit (
	val split_id: Int,
	val execution_date: LocalDate,
	val ticker: String,
	val split_from: Double,
	val split_to: Double
) {
	companion object {
		fun fromJooq(record: SPSplitsRecord): DtoPolygonSplit {
			return DtoPolygonSplit(
				split_id = record.splitId,
				execution_date = record.executionDate,
				ticker = record.ticker,
				split_from = record.splitFrom,
				split_to = record.splitTo
			)
		}
	}
}

