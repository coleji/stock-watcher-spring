package com.coleji.stockwatcher.controller;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
class TestController {
	@Autowired
	private DSLContext dslContext;

	@GetMapping("/")
	public String ping() {
		Result<Record> r = dslContext.resultQuery("select * from s_p_daily_ohlc where ohlc_id = 1").fetch();

		return r.getFirst().get("volume").toString();
	}
}