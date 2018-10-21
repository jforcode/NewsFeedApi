package com.jeevan.feed.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jeevan on 10/20/18.
 */
public class Article {
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
