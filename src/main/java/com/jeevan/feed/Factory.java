package com.jeevan.feed;

import com.jeevan.feed.dao.ArticlesQueryBuilder;
import com.jeevan.feed.dao.FeedDao;
import com.jeevan.feed.dao.IArticlesQueryBuilder;
import com.jeevan.feed.dao.IFeedDao;
import com.jeevan.feed.reqModels.FeedRequest;

/**
 * Created by jeevan on 10/21/18.
 */
public class Factory {
	private static IFeedDao feedDao;

	public static IArticlesQueryBuilder getArticlesQueryBuilder(FeedRequest req) throws Exception {
		return new ArticlesQueryBuilder(req).build();
	}

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
