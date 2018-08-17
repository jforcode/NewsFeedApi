package com.jeevan.models;

/**
 * Created by jeevan on 8/15/18.
 */
public class FilterReq {
	private String[] categoryIds;
	private String[] publishers;

	public String[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String[] getPublishers() {
		return publishers;
	}

	public void setPublishers(String[] publishers) {
		this.publishers = publishers;
	}
}
