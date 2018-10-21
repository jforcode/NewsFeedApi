package com.jeevan.feed.Meta;

import com.jeevan.feed.reqModels.SortParams;

/**
 * Created by jeevan on 10/20/18.
 */
public class Defaults {
	public static final int PAGE_NUM = 1;
	public static final int PAGE_SIZE = 20;
	public static final SortParams SORT_BY = new SortParams(SortBy.TITLE.getName(), true);

}
