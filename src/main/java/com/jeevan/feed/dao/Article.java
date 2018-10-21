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

	public long get_id() {
		return _id;
	}

	public Article set_id(long _id) {
		this._id = _id;
return this;
	}

	public String getApiSourceName() {
		return apiSourceName;
	}

	public Article setApiSourceName(String apiSourceName) {
		this.apiSourceName = apiSourceName;
return this;
	}

	public String getSourceId() {
		return sourceId;
	}

	public Article setSourceId(String sourceId) {
		this.sourceId = sourceId;
return this;
	}

	public String getSourceName() {
		return sourceName;
	}

	public Article setSourceName(String sourceName) {
		this.sourceName = sourceName;
return this;
	}

	public String getAuthor() {
		return author;
	}

	public Article setAuthor(String author) {
		this.author = author;
return this;
	}

	public String getTitle() {
		return title;
	}

	public Article setTitle(String title) {
		this.title = title;
return this;
	}

	public String getDescription() {
		return description;
	}

	public Article setDescription(String description) {
		this.description = description;
return this;
	}

	public String getUrl() {
		return url;
	}

	public Article setUrl(String url) {
		this.url = url;
return this;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public Article setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
return this;
	}

	public Long getPublishedAt() {
		return publishedAt;
	}

	public Article setPublishedAt(Long publishedAt) {
		this.publishedAt = publishedAt;
return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public Article setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public Article setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
return this;
	}

	public String getStatus() {
		return status;
	}

	public Article setStatus(String status) {
		this.status = status;
return this;
	}
}
