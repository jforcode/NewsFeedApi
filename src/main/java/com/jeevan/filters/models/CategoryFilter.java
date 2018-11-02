package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 11/1/18.
 */
public class CategoryFilter implements IFilter {
	@JsonProperty(value = "category")
	private String category;

	public CategoryFilter(String category) {
		this.category = category;
	}
}
