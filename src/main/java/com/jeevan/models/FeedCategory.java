package com.jeevan.models;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by jeevan on 8/15/18.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FeedCategory {
	BUSINESS("business", "b"),
	TECHNOLOGY("technology", "t");

	private String value;
	private String id;

	FeedCategory(String value, String id) {
		this.value = value;
		this.id = id;
	}

	public static FeedCategory getCategory(String id) {
		for (FeedCategory cat : values()) {
			if (cat.id.equals(id)) return cat;
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return value;
	}
}
