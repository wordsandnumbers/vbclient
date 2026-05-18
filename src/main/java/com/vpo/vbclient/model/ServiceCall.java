package com.vpo.vbclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceCall {

	public static final String STATE_REQUESTED = "requested";
	public static final String STATE_CANCELLED = "cancelled";
	public static final String STATE_REREQUESTED = "rerequested";
	public static final String STATE_ACKNOWLEDGED = "acknowledged";
	public static final String STATE_SERVED = "served";
	public static final String STATE_RESET = "reset";
	public static final String STATE_NONE = "none";

	public ServiceCall() {
		super();
	}

	private String state;
	@JsonProperty("room_code")
	private String roomCode;
	@JsonProperty("requested_at")
	private Date requestedAt;
	@JsonProperty("cancelled_at")
	private Date cancelledAt;
	@JsonProperty("rerequested_at")
	private Date rerequestedAt;
	@JsonProperty("acknowledged_at")
	private Date acknowledgedAt;
	@JsonProperty("served_at")
	private Date servedAt;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public Date getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}
	public Date getCancelledAt() {
		return cancelledAt;
	}
	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
	public Date getRerequestedAt() {
		return rerequestedAt;
	}
	public void setRerequestedAt(Date rerequestedAt) {
		this.rerequestedAt = rerequestedAt;
	}
	public Date getAcknowledgedAt() {
		return acknowledgedAt;
	}
	public void setAcknowledgedAt(Date acknowledgedAt) {
		this.acknowledgedAt = acknowledgedAt;
	}
	public Date getServedAt() {
		return servedAt;
	}
	public void setServedAt(Date servedAt) {
		this.servedAt = servedAt;
	}
}
