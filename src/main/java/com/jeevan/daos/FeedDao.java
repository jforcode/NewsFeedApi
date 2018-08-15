package com.jeevan.daos;

import com.jeevan.factories.DbFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedDao {

	public List<Feed> getNewsFeed() throws SQLException {
		String query = "SELECT * FROM news_feed";
		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
				.setCategory(FeedCategory.getCategory(rs.getString("category")))
				.setPublisherUrl(rs.getString("publisher_url"))
				.setPublishedOn(publishedOn != null ? publishedOn.getTime() : null);
	}
}
