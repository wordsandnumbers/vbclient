package com.vpo.vbclient.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AutocompleteSuggestion {

	public AutocompleteSuggestion() {
		super();
	}

	private String type;
	private String query;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "AutocompleteSuggestion [type=" + type + ", query=" + query + "]";
	}
}
