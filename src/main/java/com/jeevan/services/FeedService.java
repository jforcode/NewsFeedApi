package com.jeevan.services;

import com.jeevan.daos.FeedDao;
import com.jeevan.factories.DaoFactory;
import com.jeevan.models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 8/15/18.
 */
public class FeedService {
	private static final int pageSize = 20;

	private final FeedDao feedDao;

	public FeedService() {
		feedDao = DaoFactory.getFeedDao();
	}

	public FeedRes getNewsFeed(Integer pageNo, String searchTerm, SortReq sortParams, FilterReq filterParams) throws SQLException {
		List<String> categoryIds = null;
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
			publishers = filterParams.getPublishers() != null ? Arrays.asList(filterParams.getPublishers()) : null;
		}

		Integer feedCount = feedDao.getCountNewsFeed(searchTerm, categoryIds, publishers);
		List<Feed> feeds = feedDao.getNewsFeed(searchTerm, categoryIds, publishers, orderBy, descOrder, pageSize, offset);

		return new FeedRes(feedCount, pageSize, feeds);
	}

	// TODO: this should also be from db
	public List<FeedCategory> getFeedCategories() {
		return Arrays.asList(FeedCategory.values());
	}

	public PublisherRes getPublishers(Integer limit) throws SQLException {
		Integer publisherCount = feedDao.getPublishersCount();
		Map<String, List<String>> mapPublToUrls = feedDao.getPublishers(limit);

		List<Publisher> publishers = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : mapPublToUrls.entrySet()) {
			publishers.add(new Publisher(entry.getKey(), entry.getValue()));
		}

		return new PublisherRes(publisherCount, publishers);
	}
}
