package com.jeevan.feed;

import com.jeevan.feed.reqModels.SortParams;

/**
 * Created by jeevan on 10/20/18.
 */
public class Defaults {
	public static final int PAGE_NUM = 1;
	public static final int PAGE_SIZE = 20;
	public static final SortParams SORT_BY = new SortParams(SortBy.TITLE.name, true);

	public enum SortBy {
		TITLE("title"),
		SOURCE("source"),
		PUBLISHED_AT("publishedAt");

		private String name;

		SortBy(String name) {
			this.name = name;
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
}
