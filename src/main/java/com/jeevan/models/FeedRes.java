package com.jeevan.models;

import java.util.List;

/**
 * Created by jeevan on 8/16/18.
 */
public class FeedRes {
	private Integer countAllFeeds;
	private List<Feed> feeds;

	public FeedRes(Integer countAllFeeds, List<Feed> feeds) {
		this.countAllFeeds = countAllFeeds;
		this.feeds = feeds;
	}

	public Integer getCountAllFeeds() {
		return countAllFeeds;
	}

	public void setCountAllFeeds(Integer countAllFeeds) {
		this.countAllFeeds = countAllFeeds;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
}
