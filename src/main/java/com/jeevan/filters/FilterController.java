package com.jeevan.filters;

import com.jeevan.core.meta.FilterType;
import com.jeevan.filters.dao.IFiltersDao;
import com.jeevan.filters.models.FilterGroup;
import com.jeevan.filters.models.FiltersRequest;
import com.jeevan.filters.models.FiltersResponse;
import com.jeevan.filters.models.IFilter;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeevan on 10/29/18.
 */

@CrossOrigin
@RestController
public class FilterController {
	private IFiltersDao filtersDao;

	public FilterController() {
		this.filtersDao = Factory.getFiltersDao();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/filters/{filterType}")
	public FiltersResponse getAllFilters(@PathVariable String filterType) throws SQLException {
		FilterType type = FilterType.searchByType(filterType);
		if (type == null) {
			throw new IllegalArgumentException("Invalid filter type " + filterType);
		}

		List<IFilter> filters = filtersDao.getFilters(type, -1);
		int countFilters = filtersDao.getCountFilters(type);
		FilterGroup filterGroup = new FilterGroup(type, filters, countFilters);

		return new FiltersResponse(Collections.singletonList(filterGroup));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/filters")
	public FiltersResponse getFilters(@RequestBody FiltersRequest req) throws SQLException {
		int limit = req.filterLimit;
		List<FilterGroup> filters = new ArrayList<>();
		List<FilterType> filterTypes = Arrays.asList(FilterType.SOURCE, FilterType.CATEGORY, FilterType.LANGUAGE, FilterType.COUNTRY);

		for (FilterType type : filterTypes) {
			List<IFilter> sourceFilters = filtersDao.getFilters(type, limit);
			int countAllFilters = filtersDao.getCountFilters(type);
			filters.add(new FilterGroup(type, sourceFilters, countAllFilters));
		}

		return new FiltersResponse(filters);
	}

}
