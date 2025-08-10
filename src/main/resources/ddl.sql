/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `account_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `runDate` date NOT NULL,
  `symbol` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `quantity` decimal(11,2) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `fees` decimal(11,2) DEFAULT NULL,
  `amount` decimal(11,2) NOT NULL,
  `settlementDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24495343 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `c_p_eps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_p_eps` (
  `eps_id` int NOT NULL AUTO_INCREMENT,
  `ticker` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `end_date` date NOT NULL,
  `report_accepted_date_time` datetime DEFAULT NULL,
  `calculated_date` date NOT NULL,
  `basic_eps` decimal(18,2) NOT NULL,
  `diluted_eps` decimal(18,2) NOT NULL,
  PRIMARY KEY (`eps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_daily_ohlc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_daily_ohlc` (
  `ohlc_id` int NOT NULL AUTO_INCREMENT,
  `market_date` date NOT NULL,
  `ticker` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `open` decimal(11,2) DEFAULT NULL,
  `close` decimal(11,2) DEFAULT NULL,
  `high` decimal(11,2) DEFAULT NULL,
  `low` decimal(11,2) DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `number_trans` mediumint DEFAULT NULL,
  `volume_weighted_avg` double DEFAULT NULL,
  PRIMARY KEY (`ohlc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26182274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_daily_ohlc_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_daily_ohlc_day` (
  `day_id` int NOT NULL AUTO_INCREMENT,
  `market_date` date NOT NULL,
  `result_count` mediumint DEFAULT NULL,
  `fetch_date` date DEFAULT NULL,
  PRIMARY KEY (`day_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2985 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_dividends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_dividends` (
  `dividend_id` int NOT NULL AUTO_INCREMENT,
  `ticker` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `declaration_date` date NOT NULL,
  `ex_dividend_date` date NOT NULL,
  `record_date` date NOT NULL,
  `pay_date` date DEFAULT NULL,
  `cash_amount` decimal(15,2) NOT NULL,
  `dividend_type` char(2) COLLATE utf8mb4_bin NOT NULL,
  `frequency` smallint DEFAULT NULL,
  PRIMARY KEY (`dividend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=891900 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_financials_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_financials_events` (
  `financial_event_id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `filing_date` date DEFAULT NULL,
  `acceptance_datetime` datetime DEFAULT NULL,
  `timeframe` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `fiscal_period` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `fiscal_year` smallint DEFAULT NULL,
  `cik` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `company_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `source_filing_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `source_filing_file_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`financial_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=451145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
DROP TABLE IF EXISTS `s_p_financials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_financials` (
  `financial_id` int NOT NULL AUTO_INCREMENT,
  `financial_event_id` int NOT NULL,
  `financial_key` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `label` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `financial_order` smallint NOT NULL,
  `unit` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `value` decimal(18,2) NOT NULL,
  PRIMARY KEY (`financial_id`),
  CONSTRAINT `s_p_financials_ibfk_1` FOREIGN KEY (`financial_event_id`) REFERENCES `s_p_financials_events` (`financial_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21210797 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_financials_event_tickers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_financials_event_tickers` (
  `mapping_id` int NOT NULL AUTO_INCREMENT,
  `financial_event_id` int NOT NULL,
  `ticker` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`mapping_id`),
  CONSTRAINT `s_p_financials_event_tickers_ibfk_1` FOREIGN KEY (`financial_event_id`) REFERENCES `s_p_financials_events` (`financial_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=357086 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `s_p_splits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s_p_splits` (
  `split_id` int NOT NULL AUTO_INCREMENT,
  `execution_date` date NOT NULL,
  `ticker` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `split_from` float DEFAULT NULL,
  `split_to` float DEFAULT NULL,
  PRIMARY KEY (`split_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25067 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

