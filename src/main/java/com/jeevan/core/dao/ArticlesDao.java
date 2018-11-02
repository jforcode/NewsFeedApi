package com.jeevan.core.dao;

import com.jeevan.DbFactory;
import com.jeevan.core.DbUtil;
import com.jeevan.core.models.Article;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jeevan on 11/2/18.
 */
public class ArticlesDao {

	public void saveArticles(List<Article> articles) throws SQLException {
		if (articles == null || articles.size() == 0) {
			throw new IllegalArgumentException("Articles should not be null or empty");
		}

		String paramsPart = StringUtils.repeat("(?, ?, ?, ?, ?, ?, ?, ?)", ",", articles.size());
		String saveQuery = "INSERT INTO articles (api_source_name, author, description, published_at, status, title, url, url_to_image) VALUES " + paramsPart;
		List<Object> params = new ArrayList<>();

		int len = articles.size();
		for (int i = 0; i < len; i++) {
			Article article = articles.get(i);
			if (article == null) {
				throw new IllegalArgumentException("Article at position " + i + " is null");
			}

			params.addAll(Arrays.asList(article.getApiSourceName(), article.getAuthor(), article.getDescription(), new Timestamp(article.getPublishedAt()), article.getStatus(), article.getTitle(), article.getUrl(), article.getUrlToImage()));
		}

		DbUtil.ExecInsertQuery(saveQuery, params);
	}
}
