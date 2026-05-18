package com.vpo.vbclient.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SongRequestResponse {

	public SongRequestResponse() {
		super();
	}

	private boolean requested;
	private String message;

	public boolean isRequested() {
		return requested;
	}
	public void setRequested(boolean requested) {
		this.requested = requested;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
