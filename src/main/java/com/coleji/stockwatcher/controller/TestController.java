package com.coleji.stockwatcher.controller;

import com.coleji.stockwatcher.config.prop.AppProps;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return "pong";
	}
}