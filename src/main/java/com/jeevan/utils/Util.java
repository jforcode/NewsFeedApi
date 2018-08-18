package com.jeevan.utils;

/**
 * Created by jeevan on 8/15/18.
 */
public class Util {
	public static String getRepeatedQueryString(int numTimes) {
		if (numTimes <= 0) {
			return "";
		}
		String allButOne = numTimes > 1
				? String.format("%" + (numTimes - 1) + "s", " ")
				.replace(" ", ", ?")
				: "";
		return "?" + allButOne;
	}
}
