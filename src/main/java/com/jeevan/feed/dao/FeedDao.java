package com.jeevan.feed.dao;

import com.jeevan.factories.DbFactory;
import com.jeevan.feed.reqModels.FeedRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 10/21/18.
 */

public class FeedDao implements IFeedDao {
	public List<Article> getArticles(FeedRequest req) throws SQLException {
		ArticlesQueryBuilder helper = new ArticlesQueryBuilder(req).build();
		String query = helper.getQuery();
		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				int i = 1;
				for (Object param : helper.getQueryParams()) {
					stmt.setObject(i++, param);
				}

				ResultSet rs = stmt.executeQuery();
				List<Article> articles = new ArrayList<>();
				while (rs.next()) {
					articles.add(getArticleFromResultSet(helper.getMapColumnToIndex(), rs));
				}

				return articles;
			}
		}
	}

	private Article getArticleFromResultSet(Map<Article.Meta.Column, Integer> mapColumnToIndex, ResultSet rs) {
		return null;
	}

}
