package com.coleji.stockwatcher.model.polygon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public class DtoOhlcResponse {
	private Boolean adjusted;
	private Integer queryCount;
	private String request_id;
	private Integer resultsCount;
	private String status;
	private List<DtoOhlcSingleResult> results;
	private Optional<Integer> count;

	public Boolean getAdjusted() {
		return adjusted;
	}

	public void setAdjusted(Boolean adjusted) {
		this.adjusted = adjusted;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public Integer getResultsCount() {
		return resultsCount;
	}

	public void setResultsCount(Integer resultsCount) {
		this.resultsCount = resultsCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DtoOhlcSingleResult> getResults() {
		return results;
	}

	public void setResults(List<DtoOhlcSingleResult> results) {
		this.results = results;
	}

	public Optional<Integer> getCount() {
		return count;
	}

	public void setCount(Optional<Integer> count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DtoOhlcResponse{" +
				"adjusted=" + adjusted +
				", queryCount=" + queryCount +
				", request_id='" + request_id + '\'' +
				", resultsCount=" + resultsCount +
				", status='" + status + '\'' +
				", results=" + results +
				'}';
	}

	private static class DtoOhlcSingleResult {
		@JsonProperty("T")
		private String ticker;
		private Double c;
		private Double h;
		private Double l;
		private Optional<Integer> n;
		private Double o;
		@JsonProperty("t")
		private Long t;
		private Double v;
		private Optional<Double> vw;

		public String getTicker() {
			return ticker;
		}

		public void setTicker(String ticker) {
			this.ticker = ticker;
		}

		public Double getC() {
			return c;
		}

		public void setC(Double c) {
			this.c = c;
		}

		public Double getH() {
			return h;
		}

		public void setH(Double h) {
			this.h = h;
		}

		public Double getL() {
			return l;
		}

		public void setL(Double l) {
			this.l = l;
		}

		public Optional<Integer> getN() {
			return n;
		}

		public void setN(Optional<Integer> n) {
			this.n = n;
		}

		public Double getO() {
			return o;
		}

		public void setO(Double o) {
			this.o = o;
		}

		public Long getT() {
			return t;
		}

		public void setT(Long t) {
			this.t = t;
		}

		public Double getV() {
			return v;
		}

		public void setV(Double v) {
			this.v = v;
		}

		public Optional<Double> getVw() {
			return vw;
		}

		public void setVw(Optional<Double> vw) {
			this.vw = vw;
		}

		@Override
		public String toString() {
			return "DtoOhlcSingleResult{" +
					"ticker='" + ticker + '\'' +
					", c=" + c +
					", h=" + h +
					", l=" + l +
					", n=" + n +
					", o=" + o +
					", t=" + t +
					", v=" + v +
					", vw=" + vw +
					'}';
		}
	}
}
