package com.vpo.vbclient.song;

import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vpo.vbclient.model.Session;
import com.vpo.vbclient.model.Song;


public class SongClient {
	
private RestTemplate template;
	
	private static final String BASE_API_URL = "/api/v1/";
	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	private String baseURL;
	private String organization = null;
	
	public enum By {
		ARTIST("artist"),
		TITLE("title"),
		POPULAR("popularity"),
		RECENT("recently_added");
		
		private String searchString;
		
		private By(String s) {
			searchString = s;
		}
		
		public String getSearchString() {
			return searchString;
		}
	}

	public SongClient() {
		this((String) null);
	}

	public SongClient(String root) {
		super();
		if(root != null) {
			this.baseURL = root + BASE_API_URL;
		} else {
			this.baseURL = DEFAULT_ROOT_URL + BASE_API_URL;
		}
		this.template = new RestTemplate();
	}
	
	public SongClient(String root, String organization) {
		super();
		if(root != null) {
			this.baseURL = root + BASE_API_URL;
		} else {
			this.baseURL = DEFAULT_ROOT_URL + BASE_API_URL;
		}
		this.organization = organization;
		this.template = new RestTemplate();
	}
	
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}

	public Song getSongById(Integer id) {
		SongWrapper wrapper = template.getForObject(baseURL + "songs/{1}?organization={2}", SongWrapper.class, id, organization);
		return wrapper.getSong();
	}
	
	public Search findSongs(final Search search) {
		Search result = template.getForObject(buildUrl(search), 
				Search.class);
		if(result == null) {
			return null;
		}
		return mergeResult(result, search);
	}

	public Search next(final Search search) {
		if(!search.hasMore())
			return null;
		Search incr = search;
		incr.setPage(search.getPage() + 1);
		return findSongs(incr);
	}

	public Search prev(final Search search) {
		if(search.getPage() == null || search.getPage() == 1)
			return null;
		Search decr = search;
		decr.setPage(search.getPage() -1);
		return findSongs(decr);
	}
	
	public boolean addFavorite(Song song, Session session) {
		FavoriteResponse response = template.postForObject(baseURL + "songs/favorites?session={1}&song_id={2}&organization={3}", null, FavoriteResponse.class, session.getSession(), song.getId(), organization);
		return response.isAdded();
	}
	
	public void deleteFavorite(Song song, Session session) {
		template.delete(baseURL + "songs/favorites?session={1}&song_id={2}&organization={3}", session.getSession(), song.getId(), organization);
	}
	
	public Search playHistory(final Search search) {
		PlayHistoryResult result = template.getForObject(buildUrl(search), 
				PlayHistoryResult.class);
		if(result == null) {
			return null;
		}
		return mergeHistory(result, search);
	}
	


	public Search nextHistory(final Search search) {
		if(!search.hasMore())
			return null;
		Search incr = search;
		incr.setPage(search.getPage() + 1);
		return playHistory(incr);
	}

	public Search prevHistory(final Search search) {
		if(search.getPage() == null || search.getPage() == 1)
			return null;
		Search decr = search;
		decr.setPage(search.getPage() -1);
		return playHistory(decr);
	}
	
	public TagList tags() {
		return template.getForObject(baseURL + "/songs/tags?organization={1}", TagList.class, organization);
	}
	
	public LanguageList languages() {
		return template.getForObject(baseURL + "/songs/languages?organization={1}", LanguageList.class, organization);
	}
	
	
	private Search mergeResult(Search result, Search search) {
		result.setFavorites(search.isFavorites());
		result.setPlayHistory(search.isPlayHistory());
		result.setSession(search.getSession());
		return result;
	}

	private Search mergeHistory(PlayHistoryResult result, Search search) {
		Search newResult = search;
		newResult.setPage(result.page);
		newResult.setSongs(result.plays);
		return newResult;
	}
	
	private String buildUrl(Search search) {
		if(search.isBrowse()) {
			String httpUrl = baseURL + "/songs";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
			        .queryParam("prefix", search.getQuery())
			        .queryParam("by", search.getBy())
			        .queryParam("page", search.getPage())
			        .queryParam("language", search.getLanguage())
			        .queryParam("per_page", search.getPerPage())
			        .queryParam("tag", search.getTag())
			        .queryParam("organization", organization)
			        .queryParam("session", ((search.getSession() != null) ? search.getSession().getSession() : null));
			
				return builder.build().toUriString();
		} else {
		String httpUrl = baseURL + ((search.isFavorites()) ? "songs/favorites" : ((search.isPlayHistory()) ? "plays/history" : "songs/search"));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
		        .queryParam("query", search.getQuery())
		        .queryParam("page", search.getPage())
		        .queryParam("language", search.getLanguage())
		        .queryParam("per_page", search.getPerPage())
		        .queryParam("tag", search.getTag())
		        .queryParam("organization", organization)
		        .queryParam("session", ((search.getSession() != null) ? search.getSession().getSession() : null));
		
			return builder.build().toUriString();
		}
	}

//	  "session": "5b6204e6-3b82-4aec-a534-b2978933f768", 
//	  "page": 1, 
//	  "per_page": 50, 
//	  "total_pages": 1, 
//	  "total_entries": 0, 
//	  "plays": [


	@JsonIgnoreProperties(ignoreUnknown=true)
	protected static class PlayHistoryResult {
		
		public PlayHistoryResult() {
		super();
	}
		protected Integer page;
		@JsonProperty("per_page")
		protected Integer perPage;
		@JsonProperty("total_pages")
		protected Integer totalPages;
		@JsonProperty("total_entries")
		protected Integer totalEntries;
		protected List<Song> plays;
		protected String session;
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
		public List<Song> getPlays() {
			return plays;
		}
		public void setPlays(List<Song> plays) {
			this.plays = plays;
		}
		public String getSession() {
			return session;
		}
		public void setSession(String session) {
			this.session = session;
		}
		
		
		
		
		
	}
}


