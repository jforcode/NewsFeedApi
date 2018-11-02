package com.jeevan.feed.reqModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeevan.core.models.Article;

import java.util.List;

/**
 * Created by jeevan on 10/20/18.
 */
public class FeedResponse {
	@JsonProperty("articles")
	List<Article> articles;

	@JsonProperty("countArticles")
	int countArticles;

	public FeedResponse(List<Article> articles, int countArticles) {
		this.articles = articles;
		this.countArticles = countArticles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public int getCountArticles() {
		return countArticles;
	}

	public void setCountArticles(int countArticles) {
		this.countArticles = countArticles;
	}
}
