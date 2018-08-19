package com.jeevan.models;

import java.util.List;

/**
 * Created by jeevan on 8/16/18.
 */
public class FeedRes {
	private Integer countAllFeeds;
	private Integer pageSize;
	private List<Feed> feeds;

	public FeedRes(Integer countAllFeeds, Integer pageSize, List<Feed> feeds) {
		this.countAllFeeds = countAllFeeds;
		this.pageSize = pageSize;
		this.feeds = feeds;
	}

	public Integer getCountAllFeeds() {
		return countAllFeeds;
	}

	public void setCountAllFeeds(Integer countAllFeeds) {
		this.countAllFeeds = countAllFeeds;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
}
