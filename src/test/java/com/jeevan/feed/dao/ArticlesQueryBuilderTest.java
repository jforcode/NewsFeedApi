package com.jeevan.feed.dao;

import com.jeevan.common.ArticleMeta;
import com.jeevan.common.ArticleMeta.Columns;
import com.jeevan.feed.reqModels.FeedRequest;
import com.jeevan.feed.reqModels.FilterParams;
import com.jeevan.feed.reqModels.SortParams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeevan on 10/21/18.
 */

public class ArticlesQueryBuilderTest {

	@Test
	public void testQueryBuilder() throws Exception {
		SortParams sortParams = new SortParams("source", true);
		FilterParams filterParams1 = new FilterParams("source", "aljazeera");
		FilterParams filterParams2 = new FilterParams("language", "en");

		FeedRequest req = new FeedRequest();
		req.setPageNum(2);
		req.setPageSize(100);
		req.setSearchTerm("random");
		req.setSortParams(sortParams);
		req.setFilterParams(Arrays.asList(filterParams1, filterParams2));

		ArticlesQueryBuilder builder = new ArticlesQueryBuilder(req).build();

		String expectedFetchQuery = "SELECT A._id, A.api_source_name, A.source_id, A.source_name, A.author, A.title, " +
										"A.description, A.url, A.url_to_image, A.published_at, A.created_at, A.updated_at, A.status " +
										"FROM articles A " +
										"LEFT OUTER JOIN sources S ON S.s_id = A.source_id " +
										"WHERE 1 = 1 " +
										" AND (A.title LIKE '%?%' OR A.author LIKE '%?%' OR A.description LIKE '%?%' ) " +
										"AND (S.language IN (?) AND S.name IN (?)) " +
										"ORDER BY A.source_name ASC " +
										"LIMIT 100 OFFSET 100";
		List<Object> expectedFetchParams = Arrays.asList("random", "random", "random", "en", "aljazeera");
		Assert.assertEquals(expectedFetchQuery, builder.getQuery());
		Assert.assertArrayEquals(expectedFetchParams.toArray(), builder.getQueryParams().toArray());

		String expectedCountQuery = "SELECT COUNT(*) " +
										" FROM articles A " +
										"LEFT OUTER JOIN sources S ON S.s_id = A.source_id " +
										"WHERE 1 = 1 " +
										" AND (A.title LIKE '%?%' OR A.author LIKE '%?%' OR A.description LIKE '%?%' ) " +
										"AND (S.language IN (?) AND S.name IN (?))";
		List<Object> expectedCountParams = Arrays.asList("random", "random", "random", "en", "aljazeera");
		Assert.assertEquals(expectedCountQuery, builder.getCountQuery());
		Assert.assertArrayEquals(expectedCountParams.toArray(), builder.getCountParams().toArray());

		int i = 1;
		Columns[] cols = new Columns[]{Columns._ID, Columns.API_SOURCE_NAME, Columns.SOURCE_ID, Columns.SOURCE_NAME, Columns.AUTHOR, Columns.TITLE, Columns.DESCRIPTION, Columns.URL, Columns.URL_TO_IMAGE, Columns.PUBLISHED_AT, Columns.CREATED_AT, Columns.UPDATED_AT, Columns.STATUS};

		Map<String, Integer> mapColToInd = builder.getMapColumnToIndex();
		for (Columns col : cols) {
			Assert.assertEquals((int) mapColToInd.get(col.toString()), i++);
		}
	}
}
