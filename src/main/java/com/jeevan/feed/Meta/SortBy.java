package com.jeevan.feed.Meta;

public enum SortBy {
	TITLE("title"),
	SOURCE("source"),
	PUBLISHED_AT("publishedAt");

	private String name;

	SortBy(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static SortBy searchByName(String name) {
		for (SortBy sortBy : values()) {
			if (sortBy.name.equals(name)) {
				return sortBy;
			}
		}

		return null;
	}
}