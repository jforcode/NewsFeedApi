package com.jeevan.services;

import com.jeevan.daos.FeedDao;
import com.jeevan.factories.DaoFactory;
import com.jeevan.models.Feed;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedService {
	private final FeedDao feedDao;

	public FeedService() {
		feedDao = DaoFactory.getFeedDao();
	}

	public List<Feed> getNewsFeed() throws SQLException {
		return feedDao.getNewsFeed();
	}

}
