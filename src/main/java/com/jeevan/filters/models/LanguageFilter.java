package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 11/1/18.
 */
public class LanguageFilter implements IFilter {
	@JsonProperty(value = "language")
	String language;

	public LanguageFilter(String language) {
		this.language = language;
	}
}
