package com.jeevan.feed.dao;

import com.jeevan.feed.dao.Article;
import com.jeevan.feed.reqModels.FeedRequest;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeevan on 10/21/18.
 */
public interface IFeedDao {
	List<Article> getArticles(FeedRequest req) throws SQLException;
}
