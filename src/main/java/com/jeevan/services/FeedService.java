package com.jeevan.services;

import com.jeevan.daos.FeedDao;
import com.jeevan.factories.DaoFactory;
import com.jeevan.models.Feed;
import com.jeevan.models.FeedOrderableBy;
import com.jeevan.models.FilterReq;
import com.jeevan.models.SortReq;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedService {
	private static final int pageSize = 20;

	private final FeedDao feedDao;

	public FeedService() {
		feedDao = DaoFactory.getFeedDao();
	}

	public List<Feed> getNewsFeed(Integer pageNo, String searchTerm, SortReq sortParams, FilterReq filterParams) throws SQLException {
		List<String> categoryIds = null;
		Long startDate = null;
		Long endDate = null;
		List<String> publishers = null;
		FeedOrderableBy orderBy = null;
		boolean descOrder = false;
		int offset = 0;

		if (pageNo != null && pageNo > 0) {
			offset = (pageNo - 1) * pageSize;
		}
		if (sortParams != null) {
			orderBy = FeedOrderableBy.getOrderBy(sortParams.getSortBy());
			descOrder = sortParams.isDescOrder();
		}
		if (filterParams != null) {
			categoryIds = filterParams.getCategoryIds() != null ? Arrays.asList(filterParams.getCategoryIds()) : null;
			startDate = filterParams.getStartDate();
			endDate = filterParams.getEndDate();
			publishers = filterParams.getPublishers() != null ? Arrays.asList(filterParams.getPublishers()) : null;
		}

		return feedDao.getNewsFeed(searchTerm, categoryIds, startDate, endDate, publishers, orderBy, descOrder, pageSize, offset);
	}

}
