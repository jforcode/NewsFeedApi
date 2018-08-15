package com.jeevan.models;

/**
 * Created by jeevan on 8/15/18.
 */
public enum FeedOrderableBy {
	PUBLISHER("publisher", "publisher"),
	PUBLISHED_ON("publishedOn", "published_on");

	private String apiField;
	private String dbField;

	FeedOrderableBy(String apiField, String dbField) {
		this.apiField = apiField;
		this.dbField = dbField;
	}

	public String getApiField() {
		return apiField;
	}

	public String getDbField() {
		return dbField;
	}

	public static FeedOrderableBy getOrderBy(String apiField) {
		for (FeedOrderableBy orderableBy : values()) {
			if (orderableBy.apiField.equals(apiField)) return orderableBy;
		}
		return null;
	}
}
