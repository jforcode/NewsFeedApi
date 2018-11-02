package com.jeevan.feed.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 10/29/18.
 */
public interface IArticlesQueryBuilder {
	String ARTICLE_ALIAS = "A";
	String SOURCE_ALIAS = "S";
	String COL_COUNT = "count";

	IArticlesQueryBuilder build();
	String getQuery();
	String getCountQuery();
	List<Object> getQueryParams();
	List<Object> getCountParams();
	Map<String, Integer> getMapColumnToIndex();
	String getCountColumn();
}
