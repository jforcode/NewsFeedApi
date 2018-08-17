package com.jeevan.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedPostReq {
	@JsonProperty(value = "page")
	private Integer pageNum;

	@JsonProperty(value = "srch")
	private String searchTerm;

	@JsonProperty(value = "sort")
	private SortReq sortParams;

	@JsonProperty(value = "filter")
	private FilterReq filterParams;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public SortReq getSortParams() {
		return sortParams;
	}

	public void setSortParams(SortReq sortParams) {
		this.sortParams = sortParams;
	}

	public FilterReq getFilterParams() {
		return filterParams;
	}

	public void setFilterParams(FilterReq filterParams) {
		this.filterParams = filterParams;
	}
}