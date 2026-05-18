package com.vpo.vbclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Audio {

	public Audio() {
		super();
	}

	public Audio(String roomCode) {
		super();
		this.roomCode = roomCode;
	}

	@JsonProperty("room_code")
	private String roomCode;
	private Integer volume;
	@JsonProperty("pitch_shift")
	private Integer pitchShift;
	private String channels;

	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Integer getPitchShift() {
		return pitchShift;
	}
	public void setPitchShift(Integer pitchShift) {
		this.pitchShift = pitchShift;
	}
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
}
