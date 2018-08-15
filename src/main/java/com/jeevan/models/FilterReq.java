package com.jeevan.models;

/**
 * Created by jeevan on 8/15/18.
 */
public class FilterReq {
	private String[] categoryIds;
	private Long startDate;
	private Long endDate;
	private String[] publishers;

	public String[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String[] getPublishers() {
		return publishers;
	}

	public void setPublishers(String[] publishers) {
		this.publishers = publishers;
	}
}
