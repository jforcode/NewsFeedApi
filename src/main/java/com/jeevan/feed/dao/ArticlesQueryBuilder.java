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

import static java.util.stream.Collectors.joining;

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
	private String countQuery = "";
	private List<Object> queryParams;
	private List<Object> countParams;
	private Map<String, Integer> mapColumnToIndex;

	public ArticlesQueryBuilder(FeedRequest req) throws Exception {
		this.withPagination(req.getPageNum(), req.getPageSize())
			.withSearchTerm(req.getSearchTerm())
			.withSortParams(req.getSortParams())
			.withFilterParams(req.getFilterParams());
	}

	private ArticlesQueryBuilder withPagination(int pageNum, int pageSize) {
		if (pageNum <= 0) pageNum = 1;
		if (pageSize <= 0) pageSize = 0;
		offset = (pageNum - 1) * pageSize;
		limit = pageSize;
		return this;
	}

	private ArticlesQueryBuilder withSearchTerm(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			searchPart = "";
			searchParams = new ArrayList<>();
			return this;
		}

		searchPart = ARTICLE_ALIAS + "." + Columns.TITLE + " LIKE '%?%' OR " +
						 ARTICLE_ALIAS + "." + Columns.AUTHOR + " LIKE '%?%' OR " +
						 ARTICLE_ALIAS + "." + Columns.DESCRIPTION + " LIKE '%?%' ";
		searchParams = Arrays.asList(searchTerm, searchTerm, searchTerm);
		return this;
	}

	private ArticlesQueryBuilder withSortParams(SortParams sorter) throws Exception {
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

	private ArticlesQueryBuilder withFilterParams(List<FilterParams> filterParams) throws Exception {
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
		String cols = Arrays.stream(Columns.values())
						  .map(col -> ARTICLE_ALIAS + "." + col)
						  .collect(joining(", "));

		String joinPart = " FROM " + ArticleMeta.TABLE_NAME + " " + ARTICLE_ALIAS +
							  " LEFT OUTER JOIN " + SourceMeta.TABLE_NAME + " " + SOURCE_ALIAS +
							  " ON " + SOURCE_ALIAS + "." + SourceMeta.Columns.ID + " = " + ARTICLE_ALIAS + "." + Columns.SOURCE_ID;
		String wherePart = " WHERE 1 = 1 " +
							   (StringUtils.isEmpty(searchPart) ? "" : " AND (" + searchPart + ")") +
							   (StringUtils.isEmpty(filterPart) ? "" : " AND (" + filterPart + ")");
		String orderPart = StringUtils.isEmpty(orderByPart) ? "" : " ORDER BY " + orderByPart;
		String pagePart = " LIMIT " + limit + " OFFSET " + offset;

		query = "SELECT " + cols + joinPart + wherePart + orderPart + pagePart;
		countQuery = "SELECT COUNT(*) " + joinPart + wherePart;

		final int[] i = {1};
		mapColumnToIndex = new HashMap<>();
		Arrays.stream(Columns.values())
			.forEach(col -> mapColumnToIndex.put(col.getName(), i[0]++));

		queryParams = new ArrayList<>();
		countParams = new ArrayList<>();
		if (searchParams != null) {
			queryParams.addAll(searchParams);
			countParams.addAll(searchParams);
		}
		if (filterParams != null) {
			queryParams.addAll(filterParams);
			countParams.addAll(filterParams);
		}

		return this;
	}

	public String getQuery() {
		return query;
	}

	public String getCountQuery() {
		return countQuery;
	}

	public List<Object> getQueryParams() {
		return queryParams;
	}

	public List<Object> getCountParams() {
		return countParams;
	}

	public Map<String, Integer> getMapColumnToIndex() {
		return mapColumnToIndex;
	}
}
