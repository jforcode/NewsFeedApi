package com.jeevan.factories;

import com.jeevan.daos.FeedDao;

/**
 * Created by jeevan on 8/15/18.
 */
public class DaoFactory {
	private static DaoFactory mInstance;

	private FeedDao feedDao;

	private DaoFactory() {}

	public static void init() {
		mInstance = new DaoFactory();
	}

	public static FeedDao getFeedDao() {
		if (mInstance.feedDao == null) {
			mInstance.feedDao = new FeedDao();
		}
		return mInstance.feedDao;
	}

	public static void setFeedDao(FeedDao feedDao) {
		mInstance.feedDao = feedDao;
	}
}
