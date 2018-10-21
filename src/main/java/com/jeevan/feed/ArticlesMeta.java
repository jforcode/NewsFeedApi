package com.jeevan.feed;

/**
 * Created by jeevan on 10/21/18.
 */
public class ArticlesMeta {
	public static final String TABLE_NAME = "articles";

	public enum Column {
		_ID("_id"),
		API_SOURCE_NAME("api_source_name"),
		SOURCE_ID("source_id"),
		SOURCE_NAME("source_name"),
		AUTHOR("author"),
		TITLE("title"),
		DESCRIPTION("description"),
		URL("url"),
		URL_TO_IMAGE("url_to_image"),
		PUBLISHED_AT("published_at"),
		CREATED_AT("created_at"),
		UPDATED_AT("updated_at"),
		STATUS("status");

		private String name;
		Column(String name) {
			this.name = name;
		}
	}
}
