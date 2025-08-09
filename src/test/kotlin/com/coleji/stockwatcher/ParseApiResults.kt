package com.coleji.stockwatcher

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
		assertEquals(totalCt, 10292)
	}

//	@Test
//	fun parseOhlc() {
//		val allResults = parseAll("./build/resources/test/ignore/out/ohlc", DtoSplitResponse::class.java)
//		val totalCt = allResults.map {it.results.size}.reduce { a, b -> a+b }
//		assertEquals(totalCt, 10292)
//	}

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