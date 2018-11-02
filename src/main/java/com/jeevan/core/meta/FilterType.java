package com.jeevan.core.meta;

public enum FilterType {
	API_SOURCE("apiSource"),
	SOURCE("source"),
	CATEGORY("category"),
	LANGUAGE("language"),
	COUNTRY("country");

	private String type;

	FilterType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

	public static FilterType searchByType(String type) {
		for (FilterType filterType : values()) {
			if (filterType.type.equals(type)) {
				return filterType;
			}
		}

		return null;
	}
}
