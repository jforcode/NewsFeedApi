package com.jeevan.daos;

import com.jeevan.factories.DbFactory;
import com.jeevan.models.Feed;

import java.sql.*;
import java.util.List;

/**
 * Created by jeevan on 8/19/18.
 */
public class FeedMetaDao {

	public void createFeedTableAndIndices() throws SQLException {
		String createTableQuery = "" +
				" CREATE TABLE news_feed (" +
				"	_id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
				"	title VARCHAR(1024) NOT NULL, " +
				"	url VARCHAR(1024), " +
				"	publisher VARCHAR(256), " +
				"	category CHAR(1), " +
				"	publisher_url VARCHAR(1024), " +
				"	published_on DATETIME DEFAULT CURRENT_TIMESTAMP," +
				"	publisher_lower VARCHAR(256)" +
				" )";

		String createTitleIndexQuery = "" +
				" CREATE INDEX ind_title ON news_feed(title) ";
		String createPublisherIndexQuery = "" +
				" CREATE INDEX ind_publisher ON news_feed(publisher) ";
		String createPublLowerIndexQuery = "" +
				" CREATE INDEX ind_publ_lower ON news_feed(publisher_lower) ";

		try (Connection conn = DbFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createTableQuery);
			stmt.executeUpdate(createTitleIndexQuery);
			stmt.executeUpdate(createPublisherIndexQuery);
			stmt.executeUpdate(createPublLowerIndexQuery);
		}
	}

	public void insertFeeds(List<Feed> feeds) throws SQLException {
		String insertQuery = "" +
				" INSERT INTO news_feed " +
				" (title, url, publisher, category, publisher_url, published_on, publisher_lower) " +
				" VALUES (?, ?, ?, ?, ?, ?, lower(publisher)) ";

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
				for (Feed feed : feeds) {
					int i = 1;
					stmt.setString(i++, feed.getTitle());
					stmt.setString(i++, feed.getUrl());
					stmt.setString(i++, feed.getPublisher());
					stmt.setString(i++, feed.getCategory().getId());
					stmt.setString(i++, feed.getPublisherUrl());
					stmt.setTimestamp(i++, new Timestamp(feed.getPublishedOn()));

					stmt.addBatch();
				}

				stmt.executeLargeBatch();
			}
		}
	}
}
