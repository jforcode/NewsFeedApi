package com.jeevan.utils;

/**
 * Created by jeevan on 8/15/18.
 */
public class Util {
	public static String getRepeatedQueryString(int numTimes) {
		return "?" + (String.format("%" + (numTimes - 1) + "s", " ")
				.replace(" ", ", ?"));
	}
}
