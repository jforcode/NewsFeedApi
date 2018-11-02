package com.jeevan.feed.dao;

import com.jeevan.core.models.Article;

import java.util.List;

/**
 * Created by jeevan on 10/21/18.
 */
public interface IFeedDao {
	List<Article> getArticles(IArticlesQueryBuilder helper) throws Exception;
	int getCountArticles(IArticlesQueryBuilder helper) throws Exception;
}
