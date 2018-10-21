package com.jeevan.feed;

import com.jeevan.feed.reqModels.FeedRequest;
import com.jeevan.feed.reqModels.FilterParams;
import com.jeevan.feed.reqModels.SortParams;

import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 10/21/18.
 */
public class ArticlesQueryBuilder {
	private List<Object> queryParameters;
	private Map<ArticlesMeta.Column, Integer> mapColumnToIndex;

	public ArticlesQueryBuilder(FeedRequest req) {
		this.withPagination(req.getPageNum(), req.getPageSize())
			.withSearchTerm(req.getSearchTerm())
			.withSortParams(req.getSortParams())
			.withFilterParams(req.getFilterParams());
	}

	public ArticlesQueryBuilder withPagination(int pageNum, int pageSize) {
		return this;
	}

	public ArticlesQueryBuilder withSearchTerm(String searchTerm) {
		return this;
	}

	public ArticlesQueryBuilder withSortParams(SortParams sorter) {
		return this;
	}

	public ArticlesQueryBuilder withFilterParams(List<FilterParams> filterParams) {
		return this;
	}

	public ArticlesQueryBuilder build() {
		return this;
	}

	public String getQuery() {
		return "";
	}

	public List<Object> getQueryParams() {
		return null;
	}

	public Map<ArticlesMeta.Column, Integer> getMapColumnToIndex() {
		return mapColumnToIndex;
	}
}
