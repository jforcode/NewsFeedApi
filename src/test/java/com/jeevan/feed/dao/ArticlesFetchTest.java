package com.jeevan.feed.dao;

import com.jeevan.DbFactory;
import com.jeevan.core.dao.ArticlesDao;
import com.jeevan.core.models.Article;
import com.jeevan.feed.Factory;
import com.jeevan.feed.reqModels.FeedRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.jeevan.core.DbUtil.DeleteFromTables;

/**
 * Created by jeevan on 10/29/18.
 */
public class ArticlesFetchTest {

	@Test
	public void testFetchArticles() throws Exception {
		DbFactory.initialize(getClass(), "hikari.properties");
		Calendar cal = Calendar.getInstance();
		ArticlesDao articlesDao = new ArticlesDao();

		DeleteFromTables("articles");

		Article article = new Article()
			.setApiSourceName("test_source")
							  .setAuthor("jeevan")
			.setDescription("testing fetch")
			.setPublishedAt(cal.getTimeInMillis() / 1000 * 1000)
			.setStatus("active")
			.setTitle("some random test")
			.setUrl("https://www.test.com")
			.setUrlToImage("https://s3.test.com/image1.jpg");

		articlesDao.saveArticles(Collections.singletonList(article));

		FeedRequest req = new FeedRequest();
		req.Fix();

		IArticlesQueryBuilder builder = Factory.getArticlesQueryBuilder(req);
		IFeedDao feedDao = Factory.getFeedDao();
		List<Article> articles = feedDao.getArticles(builder);

		Assert.assertNotNull(articles);
		Assert.assertEquals(1, articles.size());
		Assert.assertEquals(article, articles.get(0));
	}
}
