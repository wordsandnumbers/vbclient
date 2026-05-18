package com.vpo.vbclient.song;

import com.vpo.vbclient.model.Session;

public class RouletteRequest {

	public RouletteRequest() {
		super();
	}

	private String language;
	private String tag;
	private Integer fromTop;
	private Integer perPage;
	private Boolean onlyWithPhoto;
	private Session session;

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getFromTop() {
		return fromTop;
	}
	public void setFromTop(Integer fromTop) {
		this.fromTop = fromTop;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Boolean getOnlyWithPhoto() {
		return onlyWithPhoto;
	}
	public void setOnlyWithPhoto(Boolean onlyWithPhoto) {
		this.onlyWithPhoto = onlyWithPhoto;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}
