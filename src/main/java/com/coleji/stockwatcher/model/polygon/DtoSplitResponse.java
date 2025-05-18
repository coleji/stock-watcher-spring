package com.coleji.stockwatcher.model.polygon;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DtoSplitResponse {
	private Optional<String> next_url;
	private String request_id;
	private String status;
	private List<DtoSplitSingleResult> results;

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

	public List<DtoSplitSingleResult> getResults() {
		return results;
	}

	public void setResults(List<DtoSplitSingleResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "DtoSplitResponse{" +
			"next_url=" + next_url +
			", request_id='" + request_id + '\'' +
			", status='" + status + '\'' +
			", results=" + results +
			'}';
	}

	private static class DtoSplitSingleResult {
		private String ticker;
		private LocalDate execution_date;
		private String id;
		private Integer split_from;
		private Integer split_to;

		public String getTicker() {
			return ticker;
		}

		public void setTicker(String ticker) {
			this.ticker = ticker;
		}

		public LocalDate getExecution_date() {
			return execution_date;
		}

		public void setExecution_date(LocalDate execution_date) {
			this.execution_date = execution_date;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getSplit_from() {
			return split_from;
		}

		public void setSplit_from(Integer split_from) {
			this.split_from = split_from;
		}

		public Integer getSplit_to() {
			return split_to;
		}

		public void setSplit_to(Integer split_to) {
			this.split_to = split_to;
		}

		@Override
		public String toString() {
			return "DtoSplitSingleResult{" +
				"ticker='" + ticker + '\'' +
				", execution_date=" + execution_date +
				", id='" + id + '\'' +
				", split_from=" + split_from +
				", split_to=" + split_to +
				'}';
		}
	}
}
