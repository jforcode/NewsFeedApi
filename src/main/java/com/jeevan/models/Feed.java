package com.jeevan.models;

import java.util.Objects;

/**
 * Created by jeevan on 8/15/18.
 */
public class Feed {
	private Integer id;
	private String title;
	private String url;
	private String publisher;
	private FeedCategory category;
	private String publisherUrl;
	private Long publishedOn;

	public Integer getId() {
		return id;
	}

	public Feed setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Feed setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Feed setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getPublisher() {
		return publisher;
	}

	public Feed setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public FeedCategory getCategory() {
		return category;
	}

	public Feed setCategory(FeedCategory category) {
		this.category = category;
		return this;
	}

	public String getPublisherUrl() {
		return publisherUrl;
	}

	public Feed setPublisherUrl(String publisherUrl) {
		this.publisherUrl = publisherUrl;
		return this;
	}

	public Long getPublishedOn() {
		return publishedOn;
	}

	public Feed setPublishedOn(Long publishedOn) {
		this.publishedOn = publishedOn;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Feed feed = (Feed) o;
		return Objects.equals(id, feed.id) &&
				Objects.equals(title, feed.title) &&
				Objects.equals(url, feed.url) &&
				Objects.equals(publisher, feed.publisher) &&
				Objects.equals(category, feed.category) &&
				Objects.equals(publisherUrl, feed.publisherUrl) &&
				Objects.equals(publishedOn, feed.publishedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, url, publisher, category, publisherUrl, publishedOn);
	}

	@Override
	public String toString() {
		return "Feed{" +
				"id=" + id +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", publisher='" + publisher + '\'' +
				", category=" + category +
				", publisherUrl='" + publisherUrl + '\'' +
				", publishedOn=" + publishedOn +
				'}';
	}
}
