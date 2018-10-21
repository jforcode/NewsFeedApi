package com.jeevan.feed.reqModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeevan.feed.dao.Article;

import java.util.List;

/**
 * Created by jeevan on 10/20/18.
 */
public class FeedResponse {
	@JsonProperty("articles")
	List<Article> articles;

	public FeedResponse(List<Article> articles) {
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
