package com.vpo.vbclient.song;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vpo.vbclient.model.Play;
import com.vpo.vbclient.model.Session;
import com.vpo.vbclient.model.Song;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Search {
	

	private String query;
	private String language = "";
	private String tag = "";
	private Integer page;
	@JsonProperty("per_page")
	private Integer perPage;
	@JsonProperty("total_pages")
	private Integer totalPages;
	@JsonProperty("total_entries")
	private Integer totalEntries;
	private List<Song> songs;
	private List<Play> plays;
	private Session session;
	private boolean favorites;
	private boolean playHistory;
	private boolean browse;
	private String by;
	@JsonProperty("only_with_photo")
	private Boolean onlyWithPhoto;
	@JsonProperty("include_others")
	private Boolean includeOthers;
	
	private static final Integer DEFAULT_PAGE = 1;
	private static final Integer DEFAULT_PER_PAGE = 50;


	
	public Search() {
		super();
	}
	
	public Search(Session session, boolean favorites, boolean playHistory) {
		super();
		this.session = session;
		this.favorites = favorites;
		this.playHistory = playHistory;
		this.page = DEFAULT_PAGE;
		this.perPage = DEFAULT_PER_PAGE;
	}
	
	public Search(String query) {
		super();
		this.query = query;
		this.page = DEFAULT_PAGE;
		this.perPage = DEFAULT_PER_PAGE;
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
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
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getTotalEntries() {
		return totalEntries;
	}
	public void setTotalEntries(Integer totalEntries) {
		this.totalEntries = totalEntries;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public boolean isFavorites() {
		return favorites;
	}

	public void setFavorites(boolean favorites) {
		this.favorites = favorites;
	}

	public boolean isPlayHistory() {
		return playHistory;
	}

	public void setPlayHistory(boolean playHistory) {
		this.playHistory = playHistory;
	}

	public boolean isBrowse() {
		return browse;
	}

	public void setBrowse(boolean browse) {
		this.browse = browse;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public boolean hasMore() {
		return this.totalPages - this.page > 0;
	}

	public List<Play> getPlays() {
		return plays;
	}

	public void setPlays(List<Play> plays) {
		this.plays = plays;
	}

	public Boolean getOnlyWithPhoto() {
		return onlyWithPhoto;
	}

	public void setOnlyWithPhoto(Boolean onlyWithPhoto) {
		this.onlyWithPhoto = onlyWithPhoto;
	}

	public Boolean getIncludeOthers() {
		return includeOthers;
	}

	public void setIncludeOthers(Boolean includeOthers) {
		this.includeOthers = includeOthers;
	}

}
