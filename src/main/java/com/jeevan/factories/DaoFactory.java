package com.jeevan.factories;

import com.jeevan.daos.FeedDao;
import com.jeevan.daos.FeedMetaDao;

/**
 * Created by jeevan on 8/15/18.
 */
public class DaoFactory {
	private static DaoFactory mInstance;

	private FeedDao feedDao;

	private FeedMetaDao feedMetaDao;

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

	public static FeedMetaDao getFeedMetaDao() {
		if (mInstance.feedMetaDao == null) {
			mInstance.feedMetaDao = new FeedMetaDao();
		}
		return mInstance.feedMetaDao;
	}

	public static void setFeedMetaDao(FeedMetaDao feedMetaDao) {
		mInstance.feedMetaDao = feedMetaDao;
	}
}
