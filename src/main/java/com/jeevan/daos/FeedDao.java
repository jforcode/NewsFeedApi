package com.jeevan.daos;

import com.jeevan.factories.DbFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedOrderableBy;
import com.jeevan.utils.Util;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedDao {

	public List<Feed> getNewsFeed(String titlePubSrch, List<String> categoryIds, Long startDate, Long endDate, List<String> publishers, FeedOrderableBy orderBy, boolean descOrder, int limit, int offset) throws SQLException {

		String query = buildFetchQuery(titlePubSrch, categoryIds, startDate, endDate, publishers, orderBy, descOrder);

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				buildFetchParams(stmt, titlePubSrch, categoryIds, startDate, endDate, publishers, limit, offset);
				ResultSet rs = stmt.executeQuery();
				List<Feed> feeds = new ArrayList<>();
				while (rs.next()) {
					feeds.add(getFeedFromResultSet(rs));
				}

				return feeds;
			}
		}
	}

	private String buildFetchQuery(String titlePubSrch, List<String> categoryIds, Long startDate, Long endDate, List<String> publishers, FeedOrderableBy orderBy, boolean descOrder) {

		String titlePart = StringUtils.isEmpty(titlePubSrch)
				? "" : " AND (title LIKE ? OR publisher LIKE ?) " ;
		String categoryPart = categoryIds == null || categoryIds.isEmpty()
				? "" : " AND category IN (" + Util.getRepeatedQueryString(categoryIds.size()) + ")";
		String publishedOnPart = startDate == null || endDate == null
				? "" : " AND published_on >= ? AND published_on <= ? ";
		String publisherPart = publishers == null || publishers.isEmpty()
				? "" : " AND publisher IN (" + Util.getRepeatedQueryString(publishers.size()) + ")";
		String orderByPart = orderBy == null
				? "" : " ORDER BY " + orderBy.getDbField() + " " + (descOrder ? "DESC" : "ASC");

		return "" +
				" SELECT * FROM news_feed " +
				" WHERE 1 = 1 " +
				titlePart +
				categoryPart +
				publishedOnPart +
				publisherPart +
				orderByPart +
				" LIMIT ? " +
				" OFFSET ?";
	}

	private void buildFetchParams(PreparedStatement stmt, String titlePubSrch, List<String> categoryIds, Long startDate, Long endDate, List<String> publishers, int limit, int offset) throws SQLException {

		int i = 1;
		if (!StringUtils.isEmpty(titlePubSrch)) {
			stmt.setString(i++, "%" + titlePubSrch + "%");
			stmt.setString(i++, "%" + titlePubSrch + "%");
		}
		if (categoryIds != null && !categoryIds.isEmpty()) {
			for (String catId : categoryIds) {
				stmt.setString(i++, catId);
			}
		}
		if (startDate != null && endDate != null) {
			stmt.setTimestamp(i++, new Timestamp(startDate));
			stmt.setTimestamp(i++, new Timestamp(endDate));
		}
		if (publishers != null && !publishers.isEmpty()) {
			for (String publisher : publishers) {
				stmt.setString(i++, publisher);
			}
		}

		stmt.setInt(i++, limit);
		stmt.setInt(i++, offset);
	}

	private Feed getFeedFromResultSet(ResultSet rs) throws SQLException {
		Timestamp publishedOn = rs.getTimestamp("published_on");

		return new Feed()
				.setId(rs.getInt("_id"))
				.setTitle(rs.getString("title"))
				.setUrl(rs.getString("url"))
				.setPublisher(rs.getString("publisher"))
				.setCategory(rs.getString("category"))
				.setPublisherUrl(rs.getString("publisher_url"))
				.setPublishedOn(publishedOn != null ? publishedOn.getTime() : null);
	}
}
