package com.vpo.vbclient.feedback;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedbackSubmission {

	public FeedbackSubmission() {
		super();
	}

	public FeedbackSubmission(List<FeedbackResponse> responses) {
		super();
		this.responses = responses;
	}

	private List<FeedbackResponse> responses;

	public List<FeedbackResponse> getResponses() {
		return responses;
	}
	public void setResponses(List<FeedbackResponse> responses) {
		this.responses = responses;
	}
}
