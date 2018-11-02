package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeevan.core.meta.FilterType;

import java.util.List;

/**
 * Created by jeevan on 10/29/18.
 */
public class FilterGroup {
	@JsonProperty(value = "type")
	private String filterType;

	@JsonProperty(value = "values")
	private List<IFilter> filterValues;

	@JsonProperty(value = "totalCount")
	private int allFiltersCount;

	public FilterGroup(FilterType filterType, List<IFilter> filterValues, int allFiltersCount) {
		this.filterType = filterType.getType();
		this.filterValues = filterValues;
		this.allFiltersCount = allFiltersCount;
	}
}
