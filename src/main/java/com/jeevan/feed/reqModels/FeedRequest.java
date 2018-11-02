package com.jeevan.feed.reqModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeevan.feed.Defaults;
import com.jeevan.core.meta.SortBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 10/20/18.
 */
public class FeedRequest {
	@JsonProperty(value = "page")
	private Integer pageNum;

	@JsonProperty(value = "pageSize")
	private Integer pageSize;

	@JsonProperty(value = "search")
	private String searchTerm;

	@JsonProperty(value = "sort")
	private SortParams sortParams;

	@JsonProperty(value = "filters")
	private List<FilterParams> filterParams;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public SortParams getSortParams() {
		return sortParams;
	}

	public void setSortParams(SortParams sortParams) {
		this.sortParams = sortParams;
	}

	public List<FilterParams> getFilterParams() {
		return filterParams;
	}

	public void setFilterParams(List<FilterParams> filterParams) {
		this.filterParams = filterParams;
	}

	public void Fix() {
		if (pageNum == null || pageNum <= 0) pageNum = Defaults.PAGE_NUM;
		if (pageSize == null || pageSize <= 0) pageSize = Defaults.PAGE_SIZE;
		if (searchTerm == null) searchTerm = "";
		if (sortParams == null || SortBy.searchByName(sortParams.getSortBy()) == null) {
			sortParams = Defaults.SORT_BY;
		}
		if (filterParams == null) {
			filterParams = new ArrayList<>();
		}
	}
}
