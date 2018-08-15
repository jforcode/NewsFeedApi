package com.jeevan.controllers;

import com.jeevan.factories.ServiceFactory;
import com.jeevan.services.FeedService;
import com.jeevan.models.Feed;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeevan on 8/15/18.
 */

@CrossOrigin
@RestController
public class FeedController {
	private final FeedService feedService;

	public FeedController() {
		this.feedService = ServiceFactory.getFeedService();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/feed")
	public List<Feed> getFeed() throws SQLException {
		return feedService.getNewsFeed();
	}
}
