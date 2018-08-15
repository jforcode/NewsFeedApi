package com.jeevan.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeevan.factories.ServiceFactory;
import com.jeevan.models.FilterReq;
import com.jeevan.models.SortReq;
import com.jeevan.services.FeedService;
import com.jeevan.models.Feed;
import org.springframework.util.StringUtils;
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

	@RequestMapping(method = RequestMethod.GET, path = "/feed")
	public List<Feed> getFeed(
			@RequestParam(name = "page", required = false) Integer pageNo,
			@RequestParam(name = "srch", required = false) String searchTerm,
			@RequestParam(name = "sort", required = false) String sortParamsJson,
			@RequestParam(name = "filter", required = false) String filterParamsJson)
			throws SQLException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		SortReq sortParams = StringUtils.isEmpty(sortParamsJson)
				? null : mapper.readValue(sortParamsJson, SortReq.class);
		FilterReq filterParams = StringUtils.isEmpty(filterParamsJson)
				? null : mapper.readValue(filterParamsJson, FilterReq.class);

		return feedService.getNewsFeed(pageNo, searchTerm, sortParams, filterParams);
	}
}
