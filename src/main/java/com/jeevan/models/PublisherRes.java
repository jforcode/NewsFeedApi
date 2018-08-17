package com.jeevan.models;

import java.util.List;

/**
 * Created by jeevan on 8/16/18.
 */
public class PublisherRes {
	private Integer countAllPublishers;
	private List<Publisher> publishers;

	public PublisherRes(Integer countAllPublishers, List<Publisher> publishers) {
		this.countAllPublishers = countAllPublishers;
		this.publishers = publishers;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public Integer getCountAllPublishers() {
		return countAllPublishers;
	}

	public void setCountAllPublishers(Integer countAllPublishers) {
		this.countAllPublishers = countAllPublishers;
	}
}
