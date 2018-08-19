package com.jeevan.services;

import com.jeevan.daos.FeedMetaDao;
import com.jeevan.factories.DaoFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedCategory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
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

	public void parseAndCreateFeeds(String fileName) throws IOException, SQLException {
		try (POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName))) {
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			feedMetaDao.createFeedTableAndIndices();

			List<Feed> feedsToInsert = new ArrayList<>();
			for (int i = 0; i < rows; i++) {
				Row row = sheet.getRow(i);
				feedsToInsert.add(getFeedFromRow(row));
			}

			feedMetaDao.insertFeeds(feedsToInsert);
		}
	}

	private Feed getFeedFromRow(Row row) {
		return new Feed()
		.setTitle(getCellValue(row, 1))
		.setUrl(getCellValue(row, 2))
		.setPublisher(getCellValue(row, 3))
		.setCategory(FeedCategory.getCategory(getCellValue(row, 4)))
		.setPublisherUrl(getCellValue(row, 5))
		.setPublishedOn(Long.parseLong(getCellValue(row, 6)));
	}

	private String getCellValue(Row row, int col) {
		Cell cell = row.getCell(col);
		return cell != null ? cell.getStringCellValue() : "";
	}
}
