package com.coleji.stockwatcher

import com.coleji.stockwatcher.model.polygon.DtoDividendResponse
import com.coleji.stockwatcher.model.polygon.DtoFinancialsResponse
import com.coleji.stockwatcher.model.polygon.DtoOhlcResponse
import com.coleji.stockwatcher.model.polygon.DtoSplitResponse
import com.coleji.stockwatcher.util.JacksonUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.stream.Stream

@SpringBootTest
class ParseApiResults {
	companion object {
		@BeforeAll
		@JvmStatic
		fun expandTestData() {
			if (File("./build/resources/test/ignore/out").exists()) return

			val testDataEncryptPassphrase = MysqlTest.prop.getProperty("com.coleji.stockwatcher.test-data-encrypt-phrase")
			val p = Runtime.getRuntime().exec(arrayOf<String?>("./script/decrypt-api-result.sh", testDataEncryptPassphrase))
			p.waitFor()
		}
	}

	@Test
	fun parseSplits() {
		val allResults = parseAll("./build/resources/test/ignore/out/splits", DtoSplitResponse::class.java)
		val totalCt = allResults.map {it.results.size}.reduce { a, b -> a+b }
		assertEquals(10292, totalCt )
	}

	@Test
	fun parseOhlc() {
		val allResults = parseAll("./build/resources/test/ignore/out/ohlc", DtoOhlcResponse::class.java)
		val totalCt = allResults.map { r -> if (r.results != null) r.results!!.size else 0}.reduce { a, b -> a+b }
		assertEquals(22388769, totalCt)
	}

	@Test
	fun parseDividends() {
		val allResults = parseAll("./build/resources/test/ignore/out/dividends", DtoDividendResponse::class.java)
		val totalCt = allResults.map { r -> if (r.results != null) r.results!!.size else 0}.reduce { a, b -> a+b }
		assertEquals(383762, totalCt)
	}

	@Test
	fun parseFinancials() {
		val allResults = parseAll("./build/resources/test/ignore/out/financials", DtoFinancialsResponse::class.java)
		val totalCt = allResults.map { r -> if (r.results != null) r.results!!.size else 0}.reduce { a, b -> a+b }
		assertEquals(425151, totalCt)
	}

	private fun <T> parseAll(path: String, dto: Class<T>): List<T> {
		val allFiles = Stream.of<File>(*File(path).listFiles())
			.filter { file: File? -> !file!!.isDirectory() }
			.sorted(Comparator.comparing { obj: File? -> obj!!.getName() })
			.toList()

		var results: List<T> = ArrayList()
		for (f in allFiles) {
			try {
				FileInputStream(f).use { fis ->
					val contents = fis.readAllBytes()
//					print(String(contents))
					val mapper: ObjectMapper = JacksonUtil.getObjectMapperWithModules()
					val dto = mapper.readValue(contents, dto)
					results = results.plus(dto)
				}
			} catch (e: IOException) {
				throw RuntimeException(e)
			}
		}
		return results
	}
}