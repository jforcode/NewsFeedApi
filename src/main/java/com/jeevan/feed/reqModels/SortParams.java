package com.jeevan.feed.reqModels;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 10/20/18.
 */
public class SortParams {
	@JsonProperty(value = "by")
	String sortBy;

	@JsonProperty(value = "order")
	boolean ascOrder;

	public SortParams() {

	}

	public SortParams(String sortBy, boolean ascOrder) {
		this.sortBy = sortBy;
		this.ascOrder = ascOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isDescOrder() {
		return ascOrder;
	}

	public void setAscOrder(boolean ascOrder) {
		this.ascOrder = ascOrder;
	}
}
