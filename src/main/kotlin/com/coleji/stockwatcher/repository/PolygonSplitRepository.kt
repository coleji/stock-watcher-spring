package com.coleji.stockwatcher.repository

import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.SPSplitsRecord
import org.springframework.stereotype.Repository


@Repository
class PolygonSplitRepository(
	private val dsl: DSLContext
) {
	fun getAll(): List<SPSplitsRecord> {
		val result: Result<SPSplitsRecord> = dsl.select().from(Tables.S_P_SPLITS).fetch() as Result<SPSplitsRecord>
		return result
	}
}