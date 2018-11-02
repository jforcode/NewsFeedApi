package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 11/1/18.
 */
public class FiltersRequest {
	@JsonProperty(value = "filterLimit")
	public int filterLimit;

}
