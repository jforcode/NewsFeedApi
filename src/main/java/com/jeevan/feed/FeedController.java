package com.jeevan.feed;

import com.jeevan.factories.DbFactory;
import com.jeevan.feed.daoModels.Article;
import com.jeevan.feed.reqModels.FeedRequest;
import com.jeevan.feed.reqModels.FeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 10/18/18.
 */

@CrossOrigin
@RestController
public class FeedController {
	@Autowired
	FeedDao feedDao;

	// TODO: handle exceptions better
	@RequestMapping(method = RequestMethod.POST, path = "/feed")
	public FeedResponse getFeed(@RequestBody FeedRequest req) throws Exception {
		if (req == null) {
			throw new Exception("Invalid Parameters");
		}
		req.Fix();

		List<Article> articles = feedDao.getArticles(req);
		return new FeedResponse(articles);
	}!
}
