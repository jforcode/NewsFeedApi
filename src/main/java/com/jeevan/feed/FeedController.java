package com.jeevan.feed;

import com.jeevan.core.models.Article;
import com.jeevan.feed.dao.IArticlesQueryBuilder;
import com.jeevan.feed.dao.IFeedDao;
import com.jeevan.feed.reqModels.FeedRequest;
import com.jeevan.feed.reqModels.FeedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jeevan on 10/18/18.
 */

@CrossOrigin
@RestController
public class FeedController {
	private IFeedDao feedDao;

	public FeedController() {
		feedDao = Factory.getFeedDao();
	}

	// TODO: handle exceptions better
	@RequestMapping(method = RequestMethod.POST, path = "/feed")
	public FeedResponse getFeed(@RequestBody FeedRequest req) throws Exception {
		if (req == null) {
			throw new Exception("Invalid Parameters");
		}
		req.Fix();

		IArticlesQueryBuilder builder = Factory.getArticlesQueryBuilder(req);
		List<Article> articles = feedDao.getArticles(builder);
		int countArticles = feedDao.getCountArticles(builder);
		return new FeedResponse(articles, countArticles);
	}
}
