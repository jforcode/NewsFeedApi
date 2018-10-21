package com.jeevan.feed;

import com.jeevan.feed.dao.FeedDao;
import com.jeevan.feed.dao.IFeedDao;

/**
 * Created by jeevan on 10/21/18.
 */
public class Factory {
	private static IFeedDao feedDao;

	public static IFeedDao getFeedDao() {
		if (feedDao == null) {
			feedDao = new FeedDao();
		}
		return feedDao;
	}

	public static void setFeedDao(IFeedDao feedDao) {
		Factory.feedDao = feedDao;
	}
}
