package com.jeevan.factories;

import com.jeevan.services.FeedMetaService;
import com.jeevan.services.FeedService;

/**
 * Created by jeevan on 8/15/18.
 */
public class ServiceFactory {
	private static ServiceFactory mInstance;

	private FeedService feedService;

	private FeedMetaService feedMetaService;

	private ServiceFactory() {}

	public static void init() {
		mInstance = new ServiceFactory();
	}

	public static FeedService getFeedService() {
		if (mInstance.feedService == null) {
			mInstance.feedService = new FeedService();
		}
		return mInstance.feedService;
	}

	public static void setFeedService(FeedService feedService) {
		mInstance.feedService = feedService;
	}

	public static FeedMetaService getFeedMetaService() {
		if (mInstance.feedMetaService == null) {
			mInstance.feedMetaService = new FeedMetaService();
		}
		return mInstance.feedMetaService;
	}

	public static void setFeedMetaService(FeedMetaService feedMetaService) {
		mInstance.feedMetaService = feedMetaService;
	}
}
