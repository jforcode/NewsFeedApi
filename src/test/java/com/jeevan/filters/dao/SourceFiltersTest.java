package com.jeevan.filters.dao;

import com.jeevan.DbFactory;
import com.jeevan.core.meta.FilterType;
import com.jeevan.filters.Factory;
import com.jeevan.filters.models.IFilter;
import com.jeevan.filters.models.SourceFilter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jeevan.core.DbUtil.DeleteFromTables;
import static com.jeevan.core.DbUtil.ExecInsertQuery;

/**
 * Created by jeevan on 11/1/18.
 */
public class SourceFiltersTest {
	private static List<IFilter> dbSourceFilters;

	@BeforeClass
	public static void init() throws IOException, SQLException {
		DbFactory.initialize(SourceFiltersTest.class, "hikari.properties");

		initDummyData();
		fillDb();
	}

	private static void initDummyData() {
		dbSourceFilters = Arrays.asList(
			new SourceFilter("1", "asdf"),
			new SourceFilter("3", "blkc"),
			new SourceFilter("5", "mcnv"),
			new SourceFilter("4", "oiewtng"),
			new SourceFilter("2", "qer")
		);

		dbSourceFilters.sort((o1, o2) -> ((SourceFilter) o1).sourceName.compareToIgnoreCase(((SourceFilter) o2).sourceName));
	}

	private static void fillDb() throws SQLException {
		DeleteFromTables("sources");
		List<Object> params = new ArrayList<>();
		for (IFilter filter : dbSourceFilters) {
			params.add(((SourceFilter) filter).sourceId);
			params.add(((SourceFilter) filter).sourceName);
		}

		String paramsQ = StringUtils.repeat("(?, ?)", ",", dbSourceFilters.size());
		String insertQuery = "INSERT INTO sources (s_id, name) VALUES " + paramsQ;
		ExecInsertQuery(insertQuery, params);
	}

	@Test
	public void test_filters_with_positive_limit_should_return_that_many_filters() throws SQLException, IOException {
		Test(0, 3, 3);
	}

	@Test
	public void test_negative_limit_should_return_all_filters() throws SQLException {
		Test(0, dbSourceFilters.size(), -2);
	}

	@Test
	public void test_limit_more_than_number_of_elements_should_return_all_filters() throws SQLException {
		Test(0, dbSourceFilters.size(), 10);
	}

	private void Test(int start, int end, int limit) throws SQLException {
		IFiltersDao filtersDao = Factory.getFiltersDao();
		List<IFilter> expectedFilters = dbSourceFilters.subList(start, end);

		List<IFilter> sourceFilters = filtersDao.getFilters(FilterType.SOURCE, limit);
		Assert.assertNotNull(sourceFilters);
		Assert.assertArrayEquals(expectedFilters.toArray(), sourceFilters.toArray());
	}

}
