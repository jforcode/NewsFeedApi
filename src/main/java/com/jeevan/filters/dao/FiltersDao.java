package com.jeevan.filters.dao;

import com.jeevan.DbFactory;
import com.jeevan.core.meta.FilterType;
import com.jeevan.core.meta.SourceMeta;
import com.jeevan.filters.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by jeevan on 10/29/18.
 */
public class FiltersDao implements IFiltersDao {

	@Override
	public List<IFilter> getFilters(FilterType filterType, int limit) throws SQLException {
		switch (filterType) {
			case SOURCE: return getFilters(SourceMeta.COL_ID + ", " + SourceMeta.COL_NAME, SourceMeta.COL_NAME, limit, getSourceFilterFromRs());
			case CATEGORY: return getFilters(SourceMeta.COL_CATEGORY, SourceMeta.COL_CATEGORY, limit, getCategoryFilterFromRs());
			case LANGUAGE: return getFilters(SourceMeta.COL_LANGUAGE, SourceMeta.COL_LANGUAGE, limit, getLanguageFilterFromRs());
			case COUNTRY: return getFilters(SourceMeta.COL_COUNTRY, SourceMeta.COL_COUNTRY, limit, getCountryFilterFromRs());
		}
		return null;
	}

	@Override
	public int getCountFilters(FilterType filterType) throws SQLException {
		switch (filterType) {
			case SOURCE: return getCountFilters(SourceMeta.COL_NAME);
			case CATEGORY: return getCountFilters(SourceMeta.COL_CATEGORY);
			case LANGUAGE: return getCountFilters(SourceMeta.COL_LANGUAGE);
			case COUNTRY: return getCountFilters(SourceMeta.COL_COUNTRY);
		}
		return 0;
	}

	private Function<ResultSet, IFilter> getSourceFilterFromRs() {
		return rs -> {
			try {
				String sourceId = rs.getString(SourceMeta.COL_ID);
				String sourceName = rs.getString(SourceMeta.COL_NAME);
				return new SourceFilter(sourceId, sourceName);
			} catch (SQLException e) {
				System.out.println("Error while getting source filter: " + e.getLocalizedMessage());
				return null;
			}
		};
	}

	private Function<ResultSet, IFilter> getCategoryFilterFromRs() {
		return rs -> {
			try {
				String category = rs.getString(SourceMeta.COL_CATEGORY);
				return new CategoryFilter(category);
			} catch (SQLException e) {
				System.out.println("Error while getting category filter: " + e.getLocalizedMessage());
				return null;
			}
		};
	}

	private Function<ResultSet, IFilter> getLanguageFilterFromRs() {
		return rs -> {
			try {
				String language = rs.getString(SourceMeta.COL_LANGUAGE);
				return new LanguageFilter(language);
			} catch (SQLException e) {
				System.out.println("Error while getting language filter: " + e.getLocalizedMessage());
				return null;
			}
		};
	}

	private Function<ResultSet, IFilter> getCountryFilterFromRs() {
		return rs -> {
			try {
				String country = rs.getString(SourceMeta.COL_COUNTRY);
				return new CountryFilter(country);
			} catch (SQLException e) {
				System.out.println("Error while getting country filter: " + e.getLocalizedMessage());
				return null;
			}
		};
	}

	private List<IFilter> getFilters(String colPart, String orderByPart, int limit, Function<ResultSet, IFilter> getFilterFromResultSet) throws SQLException {
		String limitPart = limit < 0 ? "" : " LIMIT " + limit;

		String getQuery = "SELECT DISTINCT " + colPart +
							  " FROM " + SourceMeta.TABLE_NAME +
							  " ORDER BY " + orderByPart +
							  limitPart;

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(getQuery)) {
				ResultSet rs = stmt.executeQuery();
				List<IFilter> filters = new ArrayList<>();

				while (rs.next()) {
					IFilter filter = getFilterFromResultSet.apply(rs);
					if (filter != null) {
						filters.add(filter);
					}
				}

				return filters;
			}
		}
	}

	private int getCountFilters(String colPart) throws SQLException {
		String countQuery = "SELECT COUNT(DISTINCT(" + colPart + ")) AS count " +
								" FROM " + SourceMeta.TABLE_NAME;

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(countQuery)) {
				ResultSet rs = stmt.executeQuery();
				return rs.next() ? rs.getInt("count") : 0;
			}
		}
	}
}
