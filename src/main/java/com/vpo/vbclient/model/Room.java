package com.vpo.vbclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Room {

	public Room() {
		super();
	}

	private String number;
	@JsonProperty("room_code")
	private String roomCode;
	private String location;
	private List<String> features;
	private Boolean enabled;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Room [roomCode=" + roomCode + ", number=" + number + ", location=" + location + "]";
	}
}
