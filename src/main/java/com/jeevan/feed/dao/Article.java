package com.jeevan.feed.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jeevan on 10/20/18.
 */
public class Article {
	public static class Meta {
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

	@JsonIgnore
	private long _id;

	private String apiSourceName;

	@JsonIgnore
	private String sourceId;

	private String sourceName;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private Long publishedAt;

	@JsonIgnore
	private Long createdAt;

	@JsonIgnore
	private Long updatedAt;

	@JsonIgnore
	private String status;
}
