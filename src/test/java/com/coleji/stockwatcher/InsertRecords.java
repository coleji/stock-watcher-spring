package com.coleji.stockwatcher;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
public class InsertRecords extends MysqlTest {
	@Autowired
	private DSLContext dslContext;

	@Test
	public void smokeTest() {
		Result<Record> r1 = dslContext.resultQuery("select * from s_p_daily_ohlc limit 1").fetch();
		System.out.println(r1.getFirst().toString());
		System.out.println(r1.getFirst().get("volume").toString());


		Result<Record> r2 = dslContext.resultQuery("select * from s_p_daily_ohlc_day limit 1").fetch();
		System.out.println(r2.getFirst().toString());

		Result<Record> r3 = dslContext.resultQuery("select * from s_p_dividends limit 1").fetch();
		System.out.println(r3.getFirst().toString());

		Result<Record> r4 = dslContext.resultQuery("select * from s_p_financials limit 1").fetch();
		System.out.println(r4.getFirst().toString());

		Result<Record> r5 = dslContext.resultQuery("select * from s_p_financials_event_tickers limit 1").fetch();
		System.out.println(r5.getFirst().toString());

		Result<Record> r6 = dslContext.resultQuery("select * from s_p_financials_events limit 1").fetch();
		System.out.println(r6.getFirst().toString());

		Result<Record> r7 = dslContext.resultQuery("select * from s_p_splits limit 1").fetch();
		System.out.println(r7.getFirst().toString());

	}
}
