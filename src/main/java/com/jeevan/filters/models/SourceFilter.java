package com.jeevan.filters.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by jeevan on 11/1/18.
 */
public class SourceFilter implements IFilter {
	@JsonProperty(value = "sourceId")
	public String sourceId;

	@JsonProperty(value = "sourceName")
	public String sourceName;

	public SourceFilter(String sourceId, String sourceName) {
		this.sourceId = sourceId;
		this.sourceName = sourceName;
	}

	@Override
	public String toString() {
		return "SourceFilter{" +
				   "sourceId='" + sourceId + '\'' +
				   ", sourceName='" + sourceName + '\'' +
				   '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SourceFilter that = (SourceFilter) o;
		return Objects.equals(sourceId, that.sourceId) &&
				   Objects.equals(sourceName, that.sourceName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceId, sourceName);
	}
}
