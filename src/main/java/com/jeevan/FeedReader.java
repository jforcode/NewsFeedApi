package com.jeevan;

import com.jeevan.controllers.FeedMetaController;
import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.DbFactory;
import com.jeevan.factories.ServiceFactory;
import com.jeevan.services.FeedMetaService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jeevan on 8/19/18.
 * Should be a different service. For this program, created in the same service.
 */
public class FeedReader {

	public static void main(String[] args) throws IOException, SQLException {
		DbFactory.initialize("src/main/resources/hikari.properties");
		DaoFactory.init();
		ServiceFactory.init();

		String excelFileName = "src/main/resources/news_feed.csv";
		FeedMetaController controller = new FeedMetaController(excelFileName);
		controller.readExcelAndCreateFeedTable();
	}
}