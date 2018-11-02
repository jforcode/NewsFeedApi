package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jeevan on 11/1/18.
 */
public class FiltersResponse {
	@JsonProperty(value = "filters")
	List<FilterGroup> filters;

	public FiltersResponse(List<FilterGroup> filters) {
		this.filters = filters;
	}
}
