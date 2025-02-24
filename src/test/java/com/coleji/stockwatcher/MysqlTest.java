package com.coleji.stockwatcher;

import org.jooq.generated.tables.SPDailyOhlc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.utility.DockerImageName;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Base class that brings up a mssql testcontainer.
 */
@ActiveProfiles(profiles = {"test", "test-db"})
@ContextConfiguration(initializers = {MysqlTest.DbConnectionInitializer.class})
public abstract class MysqlTest {
	/**
	 * If true, do not stop the container between test classes. Instead, clear out
	 * the database. If false, the mssql container is stopped and restarted between test classes.
	 * If this is disabled, you must add the spring @DirtiesContext annotation to this class -
	 * otherwise a shared spring context will use the mysql wrong port.
	 */
	private static final boolean SHARE_DB = true;

	private static final Logger logger = LoggerFactory.getLogger(MysqlTest.class);

	public static String DB_NAME = "finance_test";


	private static boolean dbStarted = false;

	private static MySQLContainer mysqlServerContainer = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql").withTag("8.0-debian")).withCommand("--max-allowed-packet=67108864");

	@BeforeAll
	public static void startDb() throws SQLException {
		if (!dbStarted) {
			try {
				getDbInitScript();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			mysqlServerContainer.withInitScript("ignore/test-data.sql").start();
			logger.info("MySql test container details: jdbcUrl={} username={} password={}", mysqlServerContainer.getJdbcUrl(), mysqlServerContainer.getUsername(), mysqlServerContainer.getPassword());
			execute("select 1");
			dbStarted = true;
		} else if (SHARE_DB) {
			// Clear all data between test classes
			clearDb();
		}
	}

	private static void createTables() {

	}

	protected static void clearDb() throws SQLException {
		String sql = """
				truncate table c_p_eps;
				truncate table s_p_daily_ohlc;
				truncate table s_p_daily_ohlc_day;
				truncate table s_p_dividends;
				truncate table s_p_financials;
				truncate table s_p_financials_event_tickers;
				truncate table s_p_financials_events;
				truncate table s_p_splits;
				""";

		if (logger.isTraceEnabled()) {
			logger.trace("Clear DB SQL=" + sql);
		} else {
			logger.info("Clearing DB tables.");
		}
		execute(sql);
	}

	@AfterAll
	public static void stopDb() {
		if (!SHARE_DB) {
			mysqlServerContainer.stop();
			dbStarted = false;
		}
	}

	protected static void execute(String sql) throws SQLException {
		try (Statement statement = mysqlServerContainer.createConnection("").createStatement()) {
			statement.execute(sql);
		}
	}

	protected static void runScript(String path) {
		JdbcDatabaseDelegate jdbcDatabaseDelegate = new JdbcDatabaseDelegate(mysqlServerContainer, "");
		ScriptUtils.runInitScript(jdbcDatabaseDelegate, path);
	}

	protected static class DbConnectionInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
					configurableApplicationContext,
					"spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
					"spring.datasource.url=" + mysqlServerContainer.getJdbcUrl(),
					"spring.datasource.username=" + mysqlServerContainer.getUsername(),
					"spring.datasource.password=" + mysqlServerContainer.getPassword()
			);
		}
	}

	private static void getDbInitScript() throws IOException, InterruptedException {
		logger.info("running shell");
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("./src/test/resources/application.properties")) {
			prop.load(fr);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String testDataEncryptPassphrase =  prop.getProperty("com.coleji.stockwatcher.test-data-encrypt-phrase");
		Process p = Runtime.getRuntime().exec(new String[]{"./decrypt-test-data.sh", testDataEncryptPassphrase});
		p.waitFor();
	}
}
