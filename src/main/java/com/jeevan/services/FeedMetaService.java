package com.jeevan.services;

import com.jeevan.daos.FeedMetaDao;
import com.jeevan.factories.DaoFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedCategory;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 8/19/18.
 */
public class FeedMetaService {
	private FeedMetaDao feedMetaDao;

	public FeedMetaService() {
		this.feedMetaDao = DaoFactory.getFeedMetaDao();
	}

	public void clearFeeds() throws SQLException {
		feedMetaDao.resetFeedTable();
	}

	public void parseAndCreateFeeds(String fileName) throws IOException, SQLException {
		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
			feedMetaDao.createFeedTableAndIndices();
			List<Feed> feedsToInsert = new ArrayList<>();

			String[] line;
			while ((line = reader.readNext()) != null) {
				feedsToInsert.add(getFeedFromRow(line));
			}
			feedMetaDao.insertFeeds(feedsToInsert);
		}
	}

	private Feed getFeedFromRow(String[] row) {
		return new Feed()
				.setTitle(row[1])
				.setUrl(row[2])
				.setPublisher(row[3])
				.setCategory(FeedCategory.getCategory(row[4]))
				.setPublisherUrl(row[5])
				.setPublishedOn(Long.parseLong(row[6]));
	}
}
