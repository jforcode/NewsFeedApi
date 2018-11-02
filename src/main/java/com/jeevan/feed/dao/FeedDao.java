package com.jeevan.feed.dao;

import com.jeevan.core.meta.ArticleMeta.Columns;
import com.jeevan.DbFactory;
import com.jeevan.core.models.Article;

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
	public List<Article> getArticles(IArticlesQueryBuilder helper) throws Exception {
		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(helper.getQuery())) {
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

	public int getCountArticles(IArticlesQueryBuilder helper) throws Exception {
		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(helper.getCountQuery())) {
				int i = 1;
				for (Object param : helper.getCountParams()) {
					stmt.setObject(i++, param);
				}

				ResultSet rs = stmt.executeQuery();
				return rs.next() ? rs.getInt(helper.getCountColumn()) : 0;
			}
		}
	}

	private Article getArticleFromResultSet(Map<String, Integer> mapColToInd, ResultSet rs) throws SQLException {
		return new Article()
			.set_id(rs.getLong(mapColToInd.get(Columns._ID.getName())))
			.setApiSourceName(rs.getString(mapColToInd.get(Columns.API_SOURCE_NAME.getName())))
			.setSourceId(rs.getString(mapColToInd.get(Columns.SOURCE_ID.getName())))
			.setSourceName(rs.getString(mapColToInd.get(Columns.SOURCE_NAME.getName())))
			.setAuthor(rs.getString(mapColToInd.get(Columns.AUTHOR.getName())))
			.setTitle(rs.getString(mapColToInd.get(Columns.TITLE.getName())))
			.setDescription(rs.getString(mapColToInd.get(Columns.DESCRIPTION.getName())))
			.setUrl(rs.getString(mapColToInd.get(Columns.URL.getName())))
			.setUrlToImage(rs.getString(mapColToInd.get(Columns.URL_TO_IMAGE.getName())))
			.setPublishedAt(rs.getTimestamp(mapColToInd.get(Columns.PUBLISHED_AT.getName())).getTime())
			.setCreatedAt(rs.getTimestamp(mapColToInd.get(Columns.CREATED_AT.getName())).getTime())
			.setUpdatedAt(rs.getTimestamp(mapColToInd.get(Columns.UPDATED_AT.getName())).getTime())
			.setStatus(rs.getString(mapColToInd.get(Columns.STATUS.getName())));
	}

}
