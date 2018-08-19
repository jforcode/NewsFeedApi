package com.jeevan.services;

import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.DbFactory;
import com.jeevan.factories.ServiceFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedRes;
import com.jeevan.models.FilterReq;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeevan on 8/16/18.
 */
public class FeedServiceTest {
	@Before
	public void init() {
		DbFactory.initialize("src/test/resources/hikari.properties");
		DaoFactory.init();
		ServiceFactory.init();
	}

	@Test
	public void testFeedService() throws SQLException {
		FeedService feedService = ServiceFactory.getFeedService();

		FilterReq filterParams = new FilterReq();
		filterParams.setPublishers(new String[]{"Game Front", "Gamenguide"});
		FeedRes feed = feedService.getNewsFeed(1, null, null, filterParams);
		Assert.assertNotNull(feed);
	}
}
