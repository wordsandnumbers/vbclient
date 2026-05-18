package com.vpo.vbclient.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SongRequest {

	public SongRequest() {
		super();
	}

	public SongRequest(String artist, String title) {
		super();
		this.artist = artist;
		this.title = title;
	}

	private String artist;
	private String title;
	private String notes;

	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
