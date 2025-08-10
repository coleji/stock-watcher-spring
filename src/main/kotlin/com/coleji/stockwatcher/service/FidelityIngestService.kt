package com.coleji.stockwatcher.service

import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.RFC4180ParserBuilder
import org.apache.commons.codec.binary.Hex
import org.springframework.stereotype.Service
import java.io.StringReader

@Service
class FidelityIngestService {
	fun processImport(bytes: ByteArray) {
		val firstThree = bytes.take(3).toByteArray()
		val firstThreeHex = Hex.encodeHexString(firstThree)

		var s = if (firstThreeHex == "efbbbf") {
			String(bytes.drop(3).toByteArray(), Charsets.UTF_8)
		} else {
			String(bytes, Charsets.UTF_8)
		}

		// fix f'd up quotes
		s = s.replace("\"Individual - TOD\" X(\\d+)", "\"Individual - TOD X$1\"")
		s = s.replace("\"SIMPLE IRA\" (\\d+)", "\"SIMPLE IRA $1\"")
		s = s.replace("\"ACCUSERVE SOLUTIONS\" (\\d+)F", "\"ACCUSERVE SOLUTIONS $1F\"")

		val csvParser = RFC4180ParserBuilder()
			.withSeparator(',')
//			.withQuoteChar('"')
			.build()

		val csvReader = CSVReaderBuilder(StringReader(s)).withCSVParser(csvParser).build()

		val headers = nextNonEmptyLine(csvReader)
		val rawRows = collectRows(csvReader, ArrayList())

		print(headers)
	}

	private fun nextNonEmptyLine(csvReader: CSVReader): Array<String>? {
		var nextLine: Array<String>?
		do {
			nextLine = csvReader.readNext()
		} while (nextLine != null && nextLine.all { s -> s=="" })
		return nextLine
	}

	private tailrec fun collectRows(csvReader: CSVReader, lines: ArrayList<Array<String>>): ArrayList<Array<String>> {
		val nextLine = csvReader.readNext() ?: return lines
		lines.addLast(nextLine)
		return collectRows(csvReader, lines)
	}
}