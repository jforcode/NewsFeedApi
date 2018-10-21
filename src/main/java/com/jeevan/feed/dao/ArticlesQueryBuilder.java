package com.jeevan.feed.dao;

import com.jeevan.common.ArticleMeta;
import com.jeevan.common.ArticleMeta.Columns;
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
	private Map<String, Integer> mapColumnToIndex;

	public ArticlesQueryBuilder(FeedRequest req) throws Exception {
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

		searchPart = ARTICLE_ALIAS + "." + Columns.TITLE + " LIKE %?% OR " +
						 ARTICLE_ALIAS + "." + Columns.AUTHOR + " LIKE %?% OR " +
						 ARTICLE_ALIAS + "." + Columns.DESCRIPTION + " LIKE %?% ";
		searchParams = Arrays.asList(searchTerm, searchTerm, searchTerm);
		return this;
	}

	public ArticlesQueryBuilder withSortParams(SortParams sorter) throws Exception {
		if (sorter == null) throw new Exception("");
		SortBy sortBy = SortBy.searchByName(sorter.getSortBy());
		if (sortBy == null) throw new Exception("");

		String sortColumn;
		switch (sortBy) {
			case TITLE: sortColumn = ARTICLE_ALIAS + "." + Columns.TITLE; break;
			case SOURCE: sortColumn = ARTICLE_ALIAS + "." + Columns.SOURCE_NAME; break;
			case PUBLISHED_AT: sortColumn = ARTICLE_ALIAS + "." + Columns.PUBLISHED_AT; break;
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
				case SOURCE: col = SOURCE_ALIAS + "." + SourceMeta.Columns.NAME; break;
				case CATEGORY: col = SOURCE_ALIAS + "." + SourceMeta.Columns.CATEGORY; break;
				case COUNTRY: col = SOURCE_ALIAS + "." + SourceMeta.Columns.COUNTRY; break;
				case LANGUAGE: col = SOURCE_ALIAS + "." + SourceMeta.Columns.LANGUAGE; break;
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
		query = " SELECT " + ARTICLE_ALIAS + "." + Columns._ID + "," +
					ARTICLE_ALIAS + "." + Columns.API_SOURCE_NAME + "," +
					ARTICLE_ALIAS + "." + Columns.SOURCE_ID + "," +
					ARTICLE_ALIAS + "." + Columns.SOURCE_NAME + "," +
					ARTICLE_ALIAS + "." + Columns.AUTHOR + "," +
					ARTICLE_ALIAS + "." + Columns.TITLE + "," +
					ARTICLE_ALIAS + "." + Columns.DESCRIPTION + "," +
					ARTICLE_ALIAS + "." + Columns.URL + "," +
					ARTICLE_ALIAS + "." + Columns.URL_TO_IMAGE + "," +
					ARTICLE_ALIAS + "." + Columns.PUBLISHED_AT + "," +
					ARTICLE_ALIAS + "." + Columns.CREATED_AT + "," +
					ARTICLE_ALIAS + "." + Columns.UPDATED_AT + "," +
					ARTICLE_ALIAS + "." + Columns.STATUS +

					" FROM " + ArticleMeta.TABLE_NAME + " " + ARTICLE_ALIAS +
					" LEFT OUTER JOIN " + SourceMeta.TABLE_NAME + " " + SOURCE_ALIAS +
					" ON " + SOURCE_ALIAS + "." + SourceMeta.Columns.ID + " = " + ARTICLE_ALIAS + "." + Columns.SOURCE_ID +
					" WHERE 1 = 1 " +
					(StringUtils.isEmpty(searchPart) ? "" : " AND (" + searchPart + ")") +
					(StringUtils.isEmpty(filterPart) ? "" : " AND (" + filterPart + ")") +
					(StringUtils.isEmpty(orderByPart) ? "" : " ORDER BY " + orderByPart) +
					" LIMIT " + limit +
					" OFFSET " + offset;

		int i = 1;
		mapColumnToIndex = new HashMap<>();
		mapColumnToIndex.put(Columns._ID.getName(), i++);
		mapColumnToIndex.put(Columns.API_SOURCE_NAME.getName(), i++);
		mapColumnToIndex.put(Columns.SOURCE_ID.getName(), i++);
		mapColumnToIndex.put(Columns.SOURCE_NAME.getName(), i++);
		mapColumnToIndex.put(Columns.AUTHOR.getName(), i++);
		mapColumnToIndex.put(Columns.TITLE.getName(), i++);
		mapColumnToIndex.put(Columns.DESCRIPTION.getName(), i++);
		mapColumnToIndex.put(Columns.URL.getName(), i++);
		mapColumnToIndex.put(Columns.URL_TO_IMAGE.getName(), i++);
		mapColumnToIndex.put(Columns.PUBLISHED_AT.getName(), i++);
		mapColumnToIndex.put(Columns.CREATED_AT.getName(), i++);
		mapColumnToIndex.put(Columns.UPDATED_AT.getName(), i++);
		mapColumnToIndex.put(Columns.STATUS.getName(), i);

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

	public Map<String, Integer> getMapColumnToIndex() {
		return mapColumnToIndex;
	}
}
