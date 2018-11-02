package com.jeevan.controllers;

import com.jeevan.factories.ServiceFactory;
import com.jeevan.services.FeedMetaService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jeevan on 8/19/18.
 */
public class FeedMetaControllerOld {
	private FeedMetaService feedMetaService;
	private String excelFileLocation;

	public FeedMetaControllerOld(String excelFileLocation) {
		this.feedMetaService = ServiceFactory.getFeedMetaService();
		this.excelFileLocation = excelFileLocation;
	}

	public void readExcelAndCreateFeedTable() throws SQLException, IOException {
		feedMetaService.clearFeeds();
		feedMetaService.parseAndCreateFeeds(excelFileLocation);
	}
}
