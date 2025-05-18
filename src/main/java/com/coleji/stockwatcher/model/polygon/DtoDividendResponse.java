package com.coleji.stockwatcher.model.polygon;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DtoDividendResponse {
	private Optional<String> next_url;
	private String request_id;
	private String status;
	private List<DtoDividendSingleResult> results;

	public Optional<String> getNext_url() {
		return next_url;
	}

	public void setNext_url(Optional<String> next_url) {
		this.next_url = next_url;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<DtoDividendSingleResult> getResults() {
		return results;
	}

	public void setResults(List<DtoDividendSingleResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "DtoDividendResponse{" +
			"next_url=" + next_url +
			", request_id='" + request_id + '\'' +
			", status='" + status + '\'' +
			", results=" + results +
			'}';
	}

	private static class DtoDividendSingleResult {
		private String ticker;
		private Double cash_amount;
		private Optional<String> currency;
		private Optional<LocalDate> declaration_date;
		private String dividend_type;
		private LocalDate ex_dividend_date;
		private Integer frequency;
		private String id;
		private Optional<LocalDate> pay_date;
		private Optional<LocalDate> record_date;

		public String getTicker() {
			return ticker;
		}

		public void setTicker(String ticker) {
			this.ticker = ticker;
		}

		public Double getCash_amount() {
			return cash_amount;
		}

		public void setCash_amount(Double cash_amount) {
			this.cash_amount = cash_amount;
		}

		public Optional<String> getCurrency() {
			return currency;
		}

		public void setCurrency(Optional<String> currency) {
			this.currency = currency;
		}

		public Optional<LocalDate> getDeclaration_date() {
			return declaration_date;
		}

		public void setDeclaration_date(Optional<LocalDate> declaration_date) {
			this.declaration_date = declaration_date;
		}

		public String getDividend_type() {
			return dividend_type;
		}

		public void setDividend_type(String dividend_type) {
			this.dividend_type = dividend_type;
		}

		public LocalDate getEx_dividend_date() {
			return ex_dividend_date;
		}

		public void setEx_dividend_date(LocalDate ex_dividend_date) {
			this.ex_dividend_date = ex_dividend_date;
		}

		public Integer getFrequency() {
			return frequency;
		}

		public void setFrequency(Integer frequency) {
			this.frequency = frequency;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Optional<LocalDate> getPay_date() {
			return pay_date;
		}

		public void setPay_date(Optional<LocalDate> pay_date) {
			this.pay_date = pay_date;
		}

		public Optional<LocalDate> getRecord_date() {
			return record_date;
		}

		public void setRecord_date(Optional<LocalDate> record_date) {
			this.record_date = record_date;
		}

		@Override
		public String toString() {
			return "DtoDividendSingleResult{" +
				"ticker='" + ticker + '\'' +
				", cash_amount=" + cash_amount +
				", currency=" + currency +
				", declaration_date=" + declaration_date +
				", dividend_type='" + dividend_type + '\'' +
				", ex_dividend_date=" + ex_dividend_date +
				", frequency=" + frequency +
				", id='" + id + '\'' +
				", pay_date=" + pay_date +
				", record_date=" + record_date +
				'}';
		}
	}
}
