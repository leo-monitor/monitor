package com.monitor.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JqQuery {

	private String orderfield;
	private String ordertype;
	private int limit;
	private int page;

	public int getOffset() {
		return (page - 1) * limit;
	}

	public String getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public int getLimit() {
		if (limit == 0)
			return 10;
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public JqQuery() {
		this.query = new ArrayList<JqCondition>();
	}

	private List<JqCondition> query;

	public List<JqCondition> getQuery() {
		return query;
	}

	public void setQuery(List<JqCondition> query) {
		this.query = query;
	}

}
