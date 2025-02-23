package com.coleji.stockwatcher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertRecords extends MysqlTest {
	@Test
	public void smokeTest() {
		Assertions.assertEquals(1, 1);
	}
}
