package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 11/1/18.
 */
public class CountryFilter implements IFilter {
	@JsonProperty(value = "country")
	String country;

	public CountryFilter(String country) {
		this.country = country;
	}
}
