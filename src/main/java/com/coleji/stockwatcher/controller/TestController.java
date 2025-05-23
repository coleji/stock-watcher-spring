package com.coleji.stockwatcher.controller;

import com.coleji.stockwatcher.config.prop.AppProps;
import com.coleji.stockwatcher.model.polygon.DtoDividendResponse;
import com.coleji.stockwatcher.model.polygon.DtoOhlcResponse;
import com.coleji.stockwatcher.model.polygon.DtoSplitResponse;
import com.coleji.stockwatcher.util.JacksonUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RestController("/")
class TestController {
	@Autowired
	private DSLContext dslContext;

	@Autowired
	private AppProps appProps;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

//	@GetMapping("/")
//	public String ping() {
//		Result<Record> r = dslContext.resultQuery("select * from s_p_daily_ohlc where ohlc_id = 1").fetch();
//
//		return r.getFirst().get("volume").toString();
//	}

	@GetMapping("/")
	public String ping() {
		List<File> allFiles = Stream.of(new File("out/sample/splits").listFiles())
			.filter(file -> !file.isDirectory())
			.sorted(Comparator.comparing(File::getName))
			.toList();

		int total = allFiles.size();
		int ct = 0;
		for (File f : allFiles) {
			try (FileInputStream fis = new FileInputStream(f)) {
				byte[] contents = fis.readAllBytes();
				ObjectMapper mapper = JacksonUtil.getObjectMapperBuilderWithModules()
						.build();
				DtoSplitResponse dto = mapper.readValue(contents, DtoSplitResponse.class);
				logger.info((++ct) + "/" + total + " - " + f.getName() + " - " + dto.getResults().size());
				if (ct==1) logger.info(dto.toString());
				break;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return "pong";
	}
}