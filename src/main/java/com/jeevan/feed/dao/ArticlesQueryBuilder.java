package com.jeevan.feed.dao;

import com.jeevan.common.ArticleMeta;
import com.jeevan.common.SourceMeta;
import com.jeevan.feed.Meta.FilterType;
import com.jeevan.feed.Meta.SortBy;
import com.jeevan.feed.reqModels.FeedRequest;
import com.jeevan.feed.reqModels.FilterParams;
import com.jeevan.feed.reqModels.SortParams;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by jeevan on 10/21/18.
 */
public class ArticlesQueryBuilder {
	private static final String ARTICLE_ALIAS = "A";
	private static final String SOURCE_ALIAS = "S";

	private String searchPart;
	private String filterPart;
	private String orderByPart;
	private int limit;
	private int offset;

	private List<Object> searchParams;
	private List<Object> filterParams;

	private String query = "";
	private List<Object> queryParams;
	private Map<ArticleMeta.Column, Integer> mapColumnToIndex;

	public ArticlesQueryBuilder(FeedRequest req) throws Exception {
		mapColumnToIndex = new HashMap<>();
		this.withPagination(req.getPageNum(), req.getPageSize())
			.withSearchTerm(req.getSearchTerm())
			.withSortParams(req.getSortParams())
			.withFilterParams(req.getFilterParams());
	}

	public ArticlesQueryBuilder withPagination(int pageNum, int pageSize) {
		if (pageNum <= 0) pageNum = 1;
		if (pageSize <= 0) pageSize = 0;
		offset = (pageNum - 1) * pageSize;
		limit = pageSize;
		return this;
	}

	public ArticlesQueryBuilder withSearchTerm(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			searchPart = "";
			searchParams = new ArrayList<>();
			return this;
		}

		searchPart = ARTICLE_ALIAS + "." + ArticleMeta.Column.TITLE + " LIKE %?% OR " +
						 ARTICLE_ALIAS + "." + ArticleMeta.Column.AUTHOR + " LIKE %?% OR " +
						 ARTICLE_ALIAS + "." + ArticleMeta.Column.DESCRIPTION + " LIKE %?% ";
		searchParams = Arrays.asList(searchTerm, searchTerm, searchTerm);
		return this;
	}

	public ArticlesQueryBuilder withSortParams(SortParams sorter) throws Exception {
		if (sorter == null) throw new Exception("");
		SortBy sortBy = SortBy.searchByName(sorter.getSortBy());
		if (sortBy == null) throw new Exception("");

		String sortColumn;
		switch (sortBy) {
			case TITLE: sortColumn = ARTICLE_ALIAS + "." + ArticleMeta.Column.TITLE; break;
			case SOURCE: sortColumn = ARTICLE_ALIAS + "." + ArticleMeta.Column.SOURCE_NAME; break;
			case PUBLISHED_AT: sortColumn = ARTICLE_ALIAS + "." + ArticleMeta.Column.PUBLISHED_AT; break;
			default: throw new Exception("");
		}

		orderByPart = sortColumn + " " + (sorter.isAscOrder() ? "ASC" : "DESC");
		return this;
	}

	public ArticlesQueryBuilder withFilterParams(List<FilterParams> filterParams) throws Exception {
		Map<String, List<String>> mapDbColToValues = new HashMap<>();
		for (FilterParams filter : filterParams) {
			List<String> values = mapDbColToValues.getOrDefault(filter.getFilterType(), new ArrayList<>());
			values.add(filter.getFilterValue());
			mapDbColToValues.put(filter.getFilterType(), values);
		}

		this.filterParams = new ArrayList<>();
		List<String> filterParts = new ArrayList<>();
		for (Map.Entry<String, List<String>> filterGroup : mapDbColToValues.entrySet()) {
			FilterType filterType = FilterType.searchByType(filterGroup.getKey());
			if (filterType == null) throw new Exception("");

			String col;
			switch (filterType) {
				case SOURCE: col = SOURCE_ALIAS + "." + SourceMeta.Column.NAME; break;
				case CATEGORY: col = SOURCE_ALIAS + "." + SourceMeta.Column.CATEGORY; break;
				case COUNTRY: col = SOURCE_ALIAS + "." + SourceMeta.Column.COUNTRY; break;
				case LANGUAGE: col = SOURCE_ALIAS + "." + SourceMeta.Column.LANGUAGE; break;
				default: throw new Exception("");
			}

			String placeholders = StringUtils.repeat("?", ",", filterGroup.getValue().size());
			filterParts.add(col + " IN (" + placeholders + ")");
			this.filterParams.addAll(filterGroup.getValue());
		}

		this.filterPart = StringUtils.join(filterParts, " AND ");
		return this;
	}

	public ArticlesQueryBuilder build() {
		query = " SELECT * FROM " + ArticleMeta.TABLE_NAME + " " + ARTICLE_ALIAS +
					" LEFT OUTER JOIN " + SourceMeta.TABLE_NAME + " " + SOURCE_ALIAS +
					" ON " + SOURCE_ALIAS + "." + SourceMeta.Column.ID + " = " + ARTICLE_ALIAS + "." + ArticleMeta.Column.SOURCE_ID +
					" WHERE 1 = 1 " +
					(StringUtils.isEmpty(searchPart) ? "" : " AND (" + searchPart + ")") +
					(StringUtils.isEmpty(filterPart) ? "" : " AND (" + filterPart + ")") +
					(StringUtils.isEmpty(orderByPart) ? "" : " ORDER BY " + orderByPart) +
					" LIMIT " + limit +
					" OFFSET " + offset ;
		return this;
	}

	public String getQuery() {
		return query;
	}

	public List<Object> getQueryParams() {
		queryParams = new ArrayList<>();
		if (searchParams != null) queryParams.addAll(searchParams);
		if (filterParams != null) queryParams.addAll(filterParams);
		return queryParams;
	}

	public Map<ArticleMeta.Column, Integer> getMapColumnToIndex() {
		return mapColumnToIndex;
	}
}
