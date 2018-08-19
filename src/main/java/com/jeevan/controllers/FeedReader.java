package com.jeevan.controllers;

import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.DbFactory;
import com.jeevan.factories.ServiceFactory;
import com.jeevan.models.Feed;
import com.jeevan.services.FeedMetaService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 8/19/18.
 */
public class FeedReader {
	private static FeedMetaService feedMetaService;

	public static void main(String[] args) throws IOException, SQLException {
		DbFactory.initialize("src/main/resources/hikari.properties");
		DaoFactory.init();
		ServiceFactory.init();
		feedMetaService = ServiceFactory.getFeedMetaService();

		feedMetaService.parseAndCreateFeeds("src/main/resources/news_feed.csv");
	}
}
