package com.jeevan.models;

import java.util.List;

/**
 * Created by jeevan on 8/16/18.
 */
public class Publisher {
	private String publisher;
	private List<String> publisherUrls;

	public Publisher(String publisher, List<String> publisherUrls) {
		this.publisher = publisher;
		this.publisherUrls = publisherUrls;
	}

	public String getPublisher() {
		return publisher;
	}

	public Publisher setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public List<String> getPublisherUrls() {
		return publisherUrls;
	}

	public void setPublisherUrls(List<String> publisherUrls) {
		this.publisherUrls = publisherUrls;
	}
}
