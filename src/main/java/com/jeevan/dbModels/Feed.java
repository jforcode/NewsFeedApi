package com.jeevan.dbModels;

import java.util.Objects;

/**
 * Created by jeevan on 8/15/18.
 */
public class Feed {
	private String rowId;
	private String title;
	private String url;
	private String publisher;
	private String category;
	private String publisherUrl;
	private Long publishedOn;

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisherUrl() {
		return publisherUrl;
	}

	public void setPublisherUrl(String publisherUrl) {
		this.publisherUrl = publisherUrl;
	}

	public Long getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(Long publishedOn) {
		this.publishedOn = publishedOn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feed feed = (Feed) o;
		return Objects.equals(rowId, feed.rowId) &&
				Objects.equals(title, feed.title) &&
				Objects.equals(url, feed.url) &&
				Objects.equals(publisher, feed.publisher) &&
				Objects.equals(category, feed.category) &&
				Objects.equals(publisherUrl, feed.publisherUrl) &&
				Objects.equals(publishedOn, feed.publishedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rowId, title, url, publisher, category, publisherUrl, publishedOn);
	}
}
