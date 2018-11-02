package com.jeevan.filters.dao;

import com.jeevan.DbFactory;
import com.jeevan.core.meta.FilterType;
import com.jeevan.filters.Factory;
import com.jeevan.filters.models.IFilter;
import com.jeevan.filters.models.SourceFilter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.jeevan.core.DbUtil.DeleteFromTables;
import static com.jeevan.core.DbUtil.ExecInsertQuery;

/**
 * Created by jeevan on 11/1/18.
 */
public class SourceFiltersTest {
	private IFiltersDao filtersDao;

	@BeforeClass
	public static void init() throws IOException, SQLException {
		DbFactory.initialize(SourceFiltersTest.class, "hikari.properties");

		DeleteFromTables("sources");
		String insertQuery = "INSERT INTO sources (s_id, name) VALUES ('1', 'asdf'), ('2', 'qer'), ('3', 'blkc'), ('4', 'oiewtng'), ('5', 'mcnv')";
		ExecInsertQuery(insertQuery, null);
	}

	@Test
	public void TestSourceFilters() throws SQLException, IOException {
		IFiltersDao filtersDao = Factory.getFiltersDao();
		IFilter[] expectedFilters = new SourceFilter[]{new SourceFilter("1", "asdf"), new SourceFilter("3", "blkc"), new SourceFilter("5", "mcnv")};

		List<IFilter> sourceFilters = filtersDao.getFilters(FilterType.SOURCE, 3);
		Assert.assertNotNull(sourceFilters);
		Assert.assertArrayEquals(expectedFilters, sourceFilters.toArray());
	}

	@Test
	public void TestWithInvalidLimit() throws SQLException {
		IFiltersDao filtersDao = Factory.getFiltersDao();
		IFilter[] expectedFilters = new SourceFilter[]{new SourceFilter("1", "asdf"), new SourceFilter("3", "blkc"), new SourceFilter("5", "mcnv"), new SourceFilter("4", "oiewtng"), new SourceFilter("2", "qer")};

		List<IFilter> sourceFilters = filtersDao.getFilters(FilterType.SOURCE, -1);
		Assert.assertNotNull(sourceFilters);
		Assert.assertArrayEquals(expectedFilters, sourceFilters.toArray());
	}

}
