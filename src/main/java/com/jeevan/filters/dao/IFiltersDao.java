package com.jeevan.filters.dao;

import com.jeevan.core.meta.FilterType;
import com.jeevan.filters.models.IFilter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeevan on 10/29/18.
 */
public interface IFiltersDao {
	List<IFilter> getFilters(FilterType filterType, int limit) throws SQLException;
	int getCountFilters(FilterType filterType) throws SQLException;
}
