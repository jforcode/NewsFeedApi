package com.jeevan.feed.reqModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jeevan on 10/20/18.
 */
public class FilterParams {
	@JsonProperty(value = "type")
	private String filterType;

	@JsonProperty(value = "value")
	private String filterValue;

	public FilterParams(String filterType, String filterValue) {
		this.filterType = filterType;
		this.filterValue = filterValue;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
}
