package com.vpo.vbclient.feedback;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedbackPromptList {

	public FeedbackPromptList() {
		super();
	}

	private List<FeedbackPrompt> prompts;

	public List<FeedbackPrompt> getPrompts() {
		return prompts;
	}
	public void setPrompts(List<FeedbackPrompt> prompts) {
		this.prompts = prompts;
	}
}
