package com.coleji.stockwatcher.controller

import com.coleji.stockwatcher.config.AppProps
import com.coleji.stockwatcher.model.dto.DtoPolygonSplit
import com.coleji.stockwatcher.model.polygon.DtoSplitResponse
import com.coleji.stockwatcher.repository.PolygonSplitRepository
import com.coleji.stockwatcher.service.FidelityIngestService
import com.coleji.stockwatcher.util.JacksonUtil
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import org.jooq.DSLContext
import org.jooq.generated.tables.records.SPSplitsRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.stream.Stream


@RestController("/")
class TestController(
	private val dslContext: DSLContext,
	private val appProps: AppProps,
	private val fidelityIngestService: FidelityIngestService,
	private val splitRepository: PolygonSplitRepository
) {
	private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	@GetMapping("/")
	fun ping(): String {
		return "pong"
	}

	@GetMapping("/splits-db")
	fun getAllSplits(): List<DtoPolygonSplit> {
		val results = splitRepository.getAll()
		return results.map { r -> DtoPolygonSplit.fromJooq(r) }
	}

	@PostMapping(value=["/import/fidelity-activity"], consumes = ["text/csv", "text/csv;charset=UTF-8"])
	fun fidelityImport(req: HttpServletRequest, @RequestBody bytes: ByteArray) {
		print(req.toString())
		fidelityIngestService.processImport(bytes)
	}

	@GetMapping("/splits")
	fun splits(): String {

		val allFiles = Stream.of<File>(*File("out/sample/splits").listFiles())
			.filter { file: File? -> !file!!.isDirectory() }
			.sorted(Comparator.comparing { obj: File? -> obj!!.getName() })
			.toList()

		val total = allFiles.size
		var ct = 0
		for (f in allFiles) {
			try {
				FileInputStream(f).use { fis ->
					val contents = fis.readAllBytes()
					print(String(contents))
					val mapper: ObjectMapper = JacksonUtil.getObjectMapperWithModules()
					val dto = mapper.readValue<DtoSplitResponse>(contents, DtoSplitResponse::class.java)
					logger.info((++ct).toString() + "/" + total + " - " + f.getName() + " - " + dto.results.size)
					if (ct == 1) logger.info(dto.toString())
				}
			} catch (e: IOException) {
				throw RuntimeException(e)
			}
		}

		return "pong"
	}
}