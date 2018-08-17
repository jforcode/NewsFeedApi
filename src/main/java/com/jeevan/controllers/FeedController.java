package com.jeevan.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeevan.factories.ServiceFactory;
import com.jeevan.models.*;
import com.jeevan.services.FeedService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

	@RequestMapping(method = RequestMethod.POST, path = "/feed")
	public FeedRes getFeed(
			@RequestBody FeedPostReq req)
			throws SQLException, IOException {

		return feedService.getNewsFeed(req.getPageNum(), req.getSearchTerm(), req.getSortParams(), req.getFilterParams());
	}

	@RequestMapping(path = "/categories")
	public List<FeedCategory> getFeedCategories() {
		return feedService.getFeedCategories();
	}

	@RequestMapping(path = "/publishers")
	public PublisherRes getPublishers(
			@RequestParam(name = "limit", required = false) Integer limit)
			throws SQLException{
		return feedService.getPublishers(limit);
	}
}
