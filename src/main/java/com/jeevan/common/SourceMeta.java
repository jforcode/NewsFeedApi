package com.jeevan.common;

/**
 * Created by jeevan on 10/21/18.
 */
public class SourceMeta {
	public static final String TABLE_NAME = "sources";

	public enum Columns {
		_ID("_id"),
		API_SOURCE_NAME("api_source_name"),
		ID("s_id"),
		NAME("name"),
		DESCRIPTION("description"),
		URL("url"),
		CATEGORY("category"),
		LANGUAGE("language"),
		COUNTRY("country"),
		CREATED_AT("created_at"),
		UPDATED_AT("updated_at"),
		STATUS("status");

		private String name;

		Columns(String name) {
			this.name = name;
		}
	}
}
