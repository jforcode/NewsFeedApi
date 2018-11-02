package com.jeevan.filters;

import com.jeevan.filters.dao.FiltersDao;
import com.jeevan.filters.dao.IFiltersDao;

/**
 * Created by jeevan on 10/29/18.
 */
public class Factory {
	private static IFiltersDao filtersDao;

	public static IFiltersDao getFiltersDao() {
		if (filtersDao == null) {
			filtersDao = new FiltersDao();
		}
		return filtersDao;
	}

	public static void setFiltersDao(IFiltersDao filtersDao) {
		Factory.filtersDao = filtersDao;
	}
}
