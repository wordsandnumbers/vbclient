package com.vpo.vbclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Session {
	
	public Session(String session) {
		super();
		this.session = session;
	}
	
	public Session() {
		super();
	}

	private String session;
	private String email;
	private String handle;
	private String color;
	@JsonProperty("hide_handle_in_queue")
	private boolean hideHandle;
	@JsonProperty("birth_year")
	private Integer birthYear;
	@JsonProperty("birth_month")
	private Integer birthMonth;
	@JsonProperty("birth_day")
	private Integer birthDay;
	@JsonProperty("zip_code")
	private String zipCode;
	@JsonProperty("prompt_for_handle_on_every_play")
	private Boolean promptForHandleOnEveryPlay;
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isHideHandle() {
		return hideHandle;
	}
	public void setHideHandle(boolean hideHandle) {
		this.hideHandle = hideHandle;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	public Integer getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}
	public Integer getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Boolean getPromptForHandleOnEveryPlay() {
		return promptForHandleOnEveryPlay;
	}
	public void setPromptForHandleOnEveryPlay(Boolean promptForHandleOnEveryPlay) {
		this.promptForHandleOnEveryPlay = promptForHandleOnEveryPlay;
	}

	@Override
	public String toString() {
		return "Session [session=" + session + ", email=" + email + ", handle="
				+ handle + "]";
	}
	
	
	
}
