package com.jeevan.daos;

import com.jeevan.factories.DbFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedOrderableBy;
import com.jeevan.models.Publisher;
import com.jeevan.utils.Util;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedDao {

	public Integer getCountNewsFeed(String titlePubSrch, List<String> categoryIds, List<String> publishers) throws SQLException {

		String titlePart = StringUtils.isEmpty(titlePubSrch)
				? "" : " AND (title LIKE ? OR publisher LIKE ?) " ;
		String categoryPart = categoryIds == null || categoryIds.isEmpty()
				? "" : " AND category IN (" + Util.getRepeatedQueryString(categoryIds.size()) + ")";
		String publisherPart = publishers == null || publishers.isEmpty()
				? "" : " AND publisher IN (" + Util.getRepeatedQueryString(publishers.size()) + ")";

		String query = "" +
				" SELECT COUNT(*) AS count " +
				" FROM news_feed " +
				" WHERE 1 = 1 " +
				titlePart +
				categoryPart +
				publisherPart;

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
				if (publishers != null && !publishers.isEmpty()) {
					for (String publisher : publishers) {
						stmt.setString(i++, publisher);
					}
				}

				ResultSet rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("count");
			}
		}
	}

	public List<Feed> getNewsFeed(String titlePubSrch, List<String> categoryIds, List<String> publishers, FeedOrderableBy orderBy, boolean descOrder, int limit, int offset) throws SQLException {

		String titlePart = StringUtils.isEmpty(titlePubSrch)
				? "" : " AND (title LIKE ? OR publisher LIKE ?) " ;
		String categoryPart = categoryIds == null || categoryIds.isEmpty()
				? "" : " AND category IN (" + Util.getRepeatedQueryString(categoryIds.size()) + ")";
		String publisherPart = publishers == null || publishers.isEmpty()
				? "" : " AND publisher IN (" + Util.getRepeatedQueryString(publishers.size()) + ")";
		String orderByPart = orderBy == null
				? "" : " ORDER BY " + orderBy.getDbField() + " " + (descOrder ? "DESC" : "ASC");

		String query = "" +
				" SELECT * FROM news_feed " +
				" WHERE 1 = 1 " +
				titlePart +
				categoryPart +
				publisherPart +
				orderByPart +
				" LIMIT ? " +
				" OFFSET ?";

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
				if (publishers != null && !publishers.isEmpty()) {
					for (String publisher : publishers) {
						stmt.setString(i++, publisher);
					}
				}

				stmt.setInt(i++, limit);
				stmt.setInt(i++, offset);

				ResultSet rs = stmt.executeQuery();
				List<Feed> feeds = new ArrayList<>();
				while (rs.next()) {
					feeds.add(getFeedFromResultSet(rs));
				}

				return feeds;
			}
		}
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

	public Integer getPublishersCount() throws SQLException {
		String query = "" +
				" SELECT COUNT(*) AS count FROM (" +
				"   SELECT DISTINCT publisher, publisher_url " +
				"   FROM news_feed " +
				"   ORDER BY publisher " +
				" ) temp_publishers ";

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("count");
			}
		}
	}

	public Map<String, List<String>> getPublishers(Integer limit) throws SQLException {
		String limitPart = limit == null || limit <= 0
				? "" : " LIMIT ? ";

		String query = "" +
				" SELECT DISTINCT publisher, publisher_url " +
				" FROM news_feed" +
				" ORDER BY publisher " +
				limitPart;

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				if (limit != null && limit > 0) {
					stmt.setInt(1, limit);
				}

				ResultSet rs = stmt.executeQuery();
				Map<String, List<String>> mapPublToUrl = new HashMap<>();
				while (rs.next()) {
					String publisher = rs.getString("publisher");
					String publisherUrl = rs.getString("publisher_url");

					List<String> currUrls = mapPublToUrl.computeIfAbsent(publisher, k -> new ArrayList<>());
					currUrls.add(publisherUrl);
				}
				return mapPublToUrl;
			}
		}
	}

}
