package com.vpo.vbclient.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedbackResponse {

	public FeedbackResponse() {
		super();
	}

	public FeedbackResponse(String guid, Integer rating) {
		super();
		this.guid = guid;
		this.rating = rating;
	}

	public FeedbackResponse(String guid, Integer rating, String notes) {
		super();
		this.guid = guid;
		this.rating = rating;
		this.notes = notes;
	}

	private String guid;
	private Integer rating;
	private String notes;

	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
