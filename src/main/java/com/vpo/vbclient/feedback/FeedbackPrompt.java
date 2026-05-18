package com.vpo.vbclient.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedbackPrompt {

	public FeedbackPrompt() {
		super();
	}

	private String guid;
	private String title;
	private String prompt;

	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	@Override
	public String toString() {
		return "FeedbackPrompt [guid=" + guid + ", title=" + title + "]";
	}
}
