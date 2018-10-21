package com.jeevan.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeevan on 8/15/18.
 */
public class SortReq {
	@JsonProperty("sortBy")
	private String sortBy;
	private boolean descOrder;

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isDescOrder() {
		return descOrder;
	}

	public void setDescOrder(boolean descOrder) {
		this.descOrder = descOrder;
	}
}
