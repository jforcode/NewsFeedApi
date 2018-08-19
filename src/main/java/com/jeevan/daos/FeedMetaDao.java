package com.jeevan.daos;

import com.jeevan.factories.DbFactory;
import com.jeevan.models.Feed;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Created by jeevan on 8/19/18.
 */
public class FeedMetaDao {
	final static Logger logger = Logger.getLogger(FeedMetaDao.class);

	private static final String TABLE_NAME = "news_feed";
	private static final String INDEX_TITLE = "ind_feed_title";
	private static final String INDEX_PUBLISHER = "ind_feed_publisher";
	private static final String INDEX_PUBLISHER_LOWER = "ind_feed_publ_lower";

	private static final String ID = "_id";
	private static final String TITLE = "title";
	private static final String URL = "url";
	private static final String PUBLISHER = "publisher";
	private static final String CATEGORY = "category";
	private static final String PUBLISHER_URL = "publisher_url";
	private static final String PUBLISHED_ON = "published_on";
	private static final String PUBLISHER_LOWER = "publisher_lower";

	public void resetFeedTable() throws SQLException {
		String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate(query);

				logger.info("Dropped table news_feed");
			}
		}
	}

	public void createFeedTableAndIndices() throws SQLException {
		String createTableQuery = String.format("" +
				" CREATE TABLE IF NOT EXISTS %s (" +
				"	%s INTEGER PRIMARY KEY AUTO_INCREMENT, " +
				"	%s VARCHAR(1024) NOT NULL, " +
				"	%s VARCHAR(1024), " +
				"	%s VARCHAR(256), " +
				"	%s CHAR(1), " +
				"	%s VARCHAR(1024), " +
				"	%s DATETIME DEFAULT CURRENT_TIMESTAMP," +
				"	%s VARCHAR(256)" +
				" )",
				TABLE_NAME, ID, TITLE, URL, PUBLISHER, CATEGORY, PUBLISHER_URL, PUBLISHED_ON, PUBLISHER_LOWER
		);

		String createTitleIndexQuery = String.format("" +
				" CREATE INDEX %s ON %s(%s) ",
				INDEX_TITLE, TABLE_NAME, TITLE
		);
		String createPublisherIndexQuery = String.format("" +
				" CREATE INDEX %s ON %s(%s) ",
				INDEX_PUBLISHER, TABLE_NAME, PUBLISHER
		);
		String createPublLowerIndexQuery = String.format("" +
				" CREATE INDEX %s ON %s(%s) ",
				INDEX_PUBLISHER_LOWER, TABLE_NAME, PUBLISHER_LOWER
		);

		try (Connection conn = DbFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createTableQuery);
			stmt.executeUpdate(createTitleIndexQuery);
			stmt.executeUpdate(createPublisherIndexQuery);
			stmt.executeUpdate(createPublLowerIndexQuery);

			logger.info(String.format(
					"Created table %s and indices %s %s %s",
					TABLE_NAME, INDEX_TITLE, INDEX_PUBLISHER, INDEX_PUBLISHER_LOWER)
			);
		}
	}

	public void insertFeeds(List<Feed> feeds) throws SQLException {
		String insertQuery = String.format("" +
				" INSERT INTO %s " +
				" (%s, %s, %s, %s, %s, %s, %s) " +
				" VALUES (?, ?, ?, ?, ?, ?, lower(%s)) ",
				TABLE_NAME, TITLE, URL, PUBLISHER, CATEGORY, PUBLISHER_URL, PUBLISHED_ON, PUBLISHER_LOWER, PUBLISHER
		);

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

					logger.info("Inserting feed: " + feed);
				}

				stmt.executeLargeBatch();
			}
		}
	}
}
